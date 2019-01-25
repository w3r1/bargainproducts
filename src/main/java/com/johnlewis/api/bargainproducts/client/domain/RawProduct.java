
package com.johnlewis.api.bargainproducts.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RawProduct {

    @JsonProperty
    private String productId;

    @JsonProperty
    private String title;

    @JsonProperty
    private RawPrice price;

    @JsonProperty
    private List<RawColorSwatch> colorSwatches;
}
