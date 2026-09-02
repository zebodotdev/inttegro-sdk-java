package com.inttegro.broadcasts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class BroadcastError {
    public String recipient;
    @JsonProperty("fix_code") public String fixCode;
    public String type;
}
