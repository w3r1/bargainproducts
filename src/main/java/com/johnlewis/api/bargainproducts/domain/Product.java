package com.johnlewis.api.bargainproducts.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Product {

    @JsonProperty
    private String productId;

    @JsonProperty
    private String title;

    @JsonProperty
    private List<ColorSwatch> colorSwatches;

    @JsonProperty
    private String nowPrice;

    @JsonProperty
    private String priceLabel;
}
