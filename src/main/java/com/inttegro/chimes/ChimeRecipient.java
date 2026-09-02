package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inttegro.common.ChimeRecipientType;
import com.inttegro.common.ChimeTransport;
import java.util.List;
import java.util.Map;

public class ChimeRecipient {
    public ChimeRecipientType type;
    public String name;
    public Phone phone;
    public Email email;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ChimeRecipient recipient = new ChimeRecipient();
        public Builder type(ChimeRecipientType type) { recipient.type = type; return this; }
        public Builder name(String name) { recipient.name = name; return this; }
        public Builder phone(Phone phone) { recipient.phone = phone; return this; }
        public Builder email(Email email) { recipient.email = email; return this; }
        public ChimeRecipient build() { return recipient; }
    }
}
