package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class Email {
    public String address;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final Email email = new Email();
        public Builder address(String address) { email.address = address; return this; }
        public Email build() { return email; }
    }
}
