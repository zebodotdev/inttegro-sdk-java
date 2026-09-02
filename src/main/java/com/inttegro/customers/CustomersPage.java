package com.inttegro.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class CustomersPage {
    public Integer number;
    public Integer size;
    public Customer[] customers;
}
