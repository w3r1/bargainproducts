package com.johnlewis.api.bargainproducts.client;

import com.johnlewis.api.bargainproducts.client.domain.RawCategory;
import com.johnlewis.api.bargainproducts.client.domain.RawProduct;
import com.johnlewis.api.bargainproducts.exception.ConsumerClientException;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class RawProductsConsumerClient {

    private static final String HTTPS = "https://";
    private static final String PORT_DELIMITER = ":";
    private static final String CATEGORY_WOMENS_DRESSES = "/v1/categories/600001506";

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    private final String womensDressesUrl;

    @Autowired
    public RawProductsConsumerClient(@Value("${server.host}") String serverHost,
                                     @Value("${server.port:}") String serverPort,
                                     @Value("${authentication.key}") String authKey,
                                     RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.build();
        headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);

        String path = HTTPS + serverHost + (isNotEmpty(serverPort) ? PORT_DELIMITER : EMPTY) + serverPort;
        String keyParam = "?key=" + authKey;
        womensDressesUrl = path + CATEGORY_WOMENS_DRESSES + "/products" + keyParam;
    }

    public List<RawProduct> getWomensDresses() {

        Optional<ResponseEntity<RawCategory>> responseEntityOptional = Try.of(() -> restTemplate.exchange(
                womensDressesUrl,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                RawCategory.class
        )).onFailure((e) -> {
            throw new ConsumerClientException("Product server not reachable, please try again later.");
        }).toJavaOptional();

        return responseEntityOptional
                .map(HttpEntity::getBody)
                .map(RawCategory::getProducts)
                .orElseThrow(() -> new ConsumerClientException("Product server error."));
    }
}
