package com.zebodotdev.commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class SpecModels {
    public static class CountrySpecification {
        @JsonProperty("country_code") public String countryCode;
        @JsonProperty("country_name") public String countryName;
        public List<String> currencies;
        @JsonProperty("payment_methods") public List<String> paymentMethods;
        @JsonProperty("payout_schedules") public List<String> payoutSchedules;
        @JsonProperty("bt_aging_specs") public List<String> btAgingSpecs;
        @JsonProperty("legal_entity_types") public List<Map<String, Object>> legalEntityTypes;
        @JsonProperty("financial_account_types") public List<Map<String, Object>> financialAccountTypes;
        @JsonProperty("id_document_types") public List<Map<String, Object>> idDocumentTypes;
    }

    public static class CountriesResponse { public Map<String, CountrySpecification> countries; }
}
