package com.zebodotdev.commerce.model;

import java.util.Map;

/**
 * Placeholder models for platform endpoints (apps/keys/sessions) where the spec
 * leaves shapes TODO. Using generic maps to avoid accidental breakage.
 */
public class PlatformModels {
    public static class CreateAppRequest { public Map<String, Object> fields; }
    public static class CreateAppResponse { public Map<String, Object> data; }

    public static class GenerateKeyRequest { public Map<String, Object> fields; }
    public static class GenerateKeyResponse { public Map<String, Object> data; }

    public static class NewSessionRequest { public Map<String, Object> fields; }
    public static class NewSessionResponse { public Map<String, Object> data; }
}
