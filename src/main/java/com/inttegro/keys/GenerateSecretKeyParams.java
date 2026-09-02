package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GenerateSecretKeyParams {
    public String label;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final GenerateSecretKeyParams params = new GenerateSecretKeyParams();
        public Builder label(String label) { params.label = label; return this; }
        public GenerateSecretKeyParams build() { return params; }
    }
}
