package com.johnlewis.api.bargainproducts.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    @JsonProperty
    private int statusCode;

    @JsonProperty
    private String errorMessage;
}
