package com.inttegro.inttegro;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;

class OpenApiCoverageTest {
    private static final Pattern PATH_LITERAL = Pattern.compile("\"(/[a-z0-9_]+(?:/[a-z0-9_]+)*)\"");
    private static final Set<String> CAPABILITY_URL_OPERATIONS = Set.of(
            "/file_links/open",
            "/upload_requests/upload"
    );

    @Test
    void sdkImplementsEveryPublicOpenApiPath() throws Exception {
        Set<String> openApiPaths = loadOpenApiPaths(findOpenApiSpec());
        Set<String> implementedPaths = loadImplementedPaths(findSdkRoot());

        List<String> missing = openApiPaths.stream()
                .filter(path -> !implementedPaths.contains(path))
                .filter(path -> !CAPABILITY_URL_OPERATIONS.contains(path))
                .sorted()
                .toList();

        assertFalse(openApiPaths.isEmpty(), "OpenAPI path parser returned no paths");
        assertFalse(
                !missing.isEmpty(),
                "Missing Java SDK coverage for OpenAPI paths:\n" + String.join("\n", missing)
        );
    }

    private static Set<String> loadOpenApiPaths(Path specPath) throws IOException {
        Set<String> paths = new HashSet<>();
        boolean inPaths = false;

        for (String line : Files.readAllLines(specPath)) {
            if (!inPaths) {
                inPaths = line.stripTrailing().equals("paths:");
                continue;
            }

            if (!line.isEmpty() && line.charAt(0) != ' ' && !line.startsWith("#")) {
                break;
            }

            String trimmed = line.stripLeading();
            if (!trimmed.startsWith("/")) {
                continue;
            }

            int colon = trimmed.indexOf(':');
            if (colon > 0) {
                paths.add(trimmed.substring(0, colon).replace("\"", "").replace("'", ""));
            }
        }

        return paths;
    }

    private static Set<String> loadImplementedPaths(Path sdkRoot) throws IOException {
        Set<String> paths = new HashSet<>();
        try (Stream<Path> files = Files.walk(sdkRoot.resolve("src/main/java"))) {
            for (Path file : files.filter(path -> path.toString().endsWith(".java")).toList()) {
                Matcher matcher = PATH_LITERAL.matcher(Files.readString(file));
                while (matcher.find()) {
                    paths.add(matcher.group(1));
                }
            }
        }
        return paths;
    }

    private static Path findOpenApiSpec() throws URISyntaxException {
        String override = System.getenv("INTTEGRO_OPENAPI_SPEC");
        if (override != null && !override.isBlank()) {
            return Path.of(override).toAbsolutePath().normalize();
        }

        Path defaultPath = Path.of("").toAbsolutePath().resolve("../../openapi/commerce.yml").normalize();
        if (Files.exists(defaultPath)) {
            return defaultPath;
        }

        for (Path start : searchStarts()) {
            for (Path current = start; current != null; current = current.getParent()) {
                Path candidate = current.resolve("openapi/commerce.yml");
                if (Files.exists(candidate)) {
                    return candidate;
                }
            }
        }

        throw new IllegalStateException("Could not find OpenAPI spec. Set INTTEGRO_OPENAPI_SPEC or run tests from sdks/java.");
    }

    private static Path findSdkRoot() throws URISyntaxException {
        for (Path start : searchStarts()) {
            for (Path current = start; current != null; current = current.getParent()) {
                Path candidate = current.resolve("src/main/java/com/inttegro/inttegro/InttegroClient.java");
                if (Files.exists(candidate)) {
                    return current;
                }
            }
        }

        throw new IllegalStateException("Could not find sdks/java root.");
    }

    private static List<Path> searchStarts() throws URISyntaxException {
        return List.of(
                Path.of("").toAbsolutePath(),
                Path.of(OpenApiCoverageTest.class.getProtectionDomain().getCodeSource().getLocation().toURI()).toAbsolutePath()
        );
    }
}
