package com.inttegro.chimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class ChimePage {
    public Integer number;
    public Integer size;
    public List<Chime> chimes;
}
