package com.johnlewis.api.bargainproducts.controller.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.johnlewis.api.bargainproducts.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    @JsonProperty
    private List<Product> products;
}
