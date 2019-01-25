
package com.johnlewis.api.bargainproducts.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RawColorSwatch {

    @JsonProperty
    private String basicColor;

    @JsonProperty
    private String color;

    @JsonProperty
    private String skuId;
}
