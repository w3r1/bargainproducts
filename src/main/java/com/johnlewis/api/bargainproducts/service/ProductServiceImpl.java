package com.johnlewis.api.bargainproducts.service;

import com.johnlewis.api.bargainproducts.client.RawProductsConsumerClient;
import com.johnlewis.api.bargainproducts.client.domain.RawProduct;
import com.johnlewis.api.bargainproducts.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RawProductsConsumerClient rawProductsConsumerClient;

    @Override
    public List<Product> getWomensDressesSortedByHighestDiscount() {

        List<RawProduct> womensDresses = rawProductsConsumerClient.getWomensDresses();
        // TODO

        return null;
    }
}
