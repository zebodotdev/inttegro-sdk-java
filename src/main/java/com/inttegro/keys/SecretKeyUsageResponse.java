package com.inttegro.keys;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class SecretKeyUsageResponse {
    public SecretKey key;
    public SecretKeyUsagePage usage;
}
