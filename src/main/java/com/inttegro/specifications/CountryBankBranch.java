package com.inttegro.specifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class CountryBankBranch {
    public String id;
    public String name;
    @JsonProperty("sort_code") public String sortCode;
}
