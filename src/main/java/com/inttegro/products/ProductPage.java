package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ProductPage {
    public Integer number;
    public Integer size;
    public List<Product> products;
}
