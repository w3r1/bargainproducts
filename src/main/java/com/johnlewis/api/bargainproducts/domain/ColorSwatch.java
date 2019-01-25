package com.johnlewis.api.bargainproducts.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ColorSwatch {

    @JsonProperty
    String color;

    @JsonProperty
    String rgbColor;

    @JsonProperty
    String skuid;
}
