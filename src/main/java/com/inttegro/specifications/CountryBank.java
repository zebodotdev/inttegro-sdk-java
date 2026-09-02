package com.inttegro.specifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class CountryBank {
    public String id;
    public String name;
    @JsonProperty("swift_code") public String swiftCode;
    @JsonProperty("sort_code_prefix") public String sortCodePrefix;
    public List<CountryBankBranch> branches;
}
