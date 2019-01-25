package com.johnlewis.api.bargainproducts.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Product implements Comparable<Product> {

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

    @JsonIgnore
    private String thenPrice;

    @JsonIgnore
    private String wasPrice;

    @JsonIgnore
    private Float priceDiscountPerc;

    @JsonIgnore
    private float priceReduction;

    @Override
    public int compareTo(Product o) {

        if (this.priceReduction > o.priceReduction) {
            return -1;
        } else if (this.priceReduction < o.priceReduction) {
            return 1;
        } else {
            return 0;
        }
    }
}
