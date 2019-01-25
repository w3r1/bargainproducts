
package com.johnlewis.api.bargainproducts.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RawPrice {

    @JsonProperty
    private String was;

    @JsonProperty
    private String then1;

    @JsonProperty
    private String then2;

    @JsonProperty
    private Object now;

    @JsonProperty
    private String currency;
}
