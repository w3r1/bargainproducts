
package com.johnlewis.api.bargainproducts.client.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RawCategory {

    @JsonProperty
    private List<RawProduct> products;
}
