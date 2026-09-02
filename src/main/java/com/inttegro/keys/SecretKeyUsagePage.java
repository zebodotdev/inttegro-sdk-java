package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SecretKeyUsagePage {
    public Integer number;
    public Integer size;
    public Integer count;
    public Integer total;
    @JsonProperty("has_more")
    public Boolean hasMore;
    public List<SecretKeyUsageRow> rows;
}
