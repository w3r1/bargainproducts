package com.johnlewis.api.bargainproducts.service;

import com.johnlewis.api.bargainproducts.domain.LabelType;
import com.johnlewis.api.bargainproducts.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> getWomensDressesSortedByHighestDiscount(LabelType labelType);
}
