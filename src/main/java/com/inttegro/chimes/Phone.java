package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.ChimeRecipientType;
import com.inttegro.common.ChimeTransport;
import java.util.List;
import java.util.Map;

public class Phone {
    public String number;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final Phone phone = new Phone();
        public Builder number(String number) { phone.number = number; return this; }
        public Phone build() { return phone; }
    }
}
