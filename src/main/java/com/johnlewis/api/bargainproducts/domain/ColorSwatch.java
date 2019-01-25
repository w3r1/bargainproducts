package com.johnlewis.api.bargainproducts.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColorSwatch {

    @JsonProperty
    String color;

    @JsonProperty
    String rgbColor;

    @JsonProperty
    String skuid;
}
