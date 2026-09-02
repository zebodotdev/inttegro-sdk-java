package com.inttegro.specifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class CountryBankDirectory {
    @JsonProperty("bank_account_type") public String bankAccountType;
    @JsonProperty("code_scheme") public String codeScheme;
    public List<CountryBank> items;
}
