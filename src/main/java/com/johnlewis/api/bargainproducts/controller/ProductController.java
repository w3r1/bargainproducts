package com.johnlewis.api.bargainproducts.controller;

import com.johnlewis.api.bargainproducts.controller.domain.ProductResponse;
import com.johnlewis.api.bargainproducts.domain.LabelType;
import com.johnlewis.api.bargainproducts.domain.Product;
import com.johnlewis.api.bargainproducts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/bargainproducts")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/womensdresses", method = GET)
    public ResponseEntity<ProductResponse> getWomensDressesSortedByHighestDiscount(
            @RequestParam(value = "labelType", required = false) LabelType labelType) {

        List<Product> womensDresses =
                productService.getWomensDressesSortedByHighestDiscount(labelType);

        return new ResponseEntity<>(new ProductResponse(womensDresses), HttpStatus.OK);
    }
}
