package com.inttegro.consumer;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SemanticCollectionsTest {
    private static final List<String> COLLECTION_TYPES = List.of(
            "CustomData.java",
            "CustomDataInput.java",
            "CustomDataPatch.java",
            "JsonData.java",
            "MessageHeaders.java",
            "PayoutDestinations.java",
            "FileMetadata.java",
            "CountrySpecifications.java",
            "CustomerBalance.java",
            "ProductDimensionDetails.java",
            "PurchaseIntentVariant.java"
    );

    @Test
    void publicDomainFieldsAndResourceMethodsDoNotExposeRawMaps() throws IOException {
        Path sourceRoot = Path.of("src/main/java/com/inttegro");
        try (var paths = Files.walk(sourceRoot)) {
            List<String> violations = paths
                    .filter(path -> path.toString().endsWith(".java"))
                    .filter(path -> !path.getFileName().toString().equals("Client.java"))
                    .filter(path -> !COLLECTION_TYPES.contains(path.getFileName().toString()))
                    .flatMap(path -> {
                        try {
                            return Files.readAllLines(path).stream()
                                    .filter(line -> line.contains("public ") && line.contains("Map<"))
                                    .map(line -> path + ": " + line.trim());
                        } catch (IOException exception) {
                            throw new RuntimeException(exception);
                        }
                    })
                    .toList();
            assertTrue(violations.isEmpty(), () -> "raw public map types: " + violations);
        }
    }
}
