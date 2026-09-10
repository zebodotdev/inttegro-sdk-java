package com.inttegro.orders;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderCreatedFrom {
    public String source;
    @JsonProperty("resource_type") public OrderCreatedFromResourceType resourceType;
    @JsonProperty("resource_id") public String resourceId;
}
