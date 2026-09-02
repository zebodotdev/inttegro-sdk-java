package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ProductPriceSummary {
    public String id;
    public String label;
    public ProductPriceAmount nominal;
}
