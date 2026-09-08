package com.inttegro.filereferences;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class FileReferenceError {
    public String type;
    public String code;
    public String message;
    @JsonProperty("fix_code") public String fixCode;
}
