package com.johnlewis.api.bargainproducts.controller;

import com.johnlewis.api.bargainproducts.controller.domain.ProductResponse;
import com.johnlewis.api.bargainproducts.domain.Product;
import com.johnlewis.api.bargainproducts.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/bargainproducts")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/womendresses", method = GET)
    public ResponseEntity<ProductResponse> getWomensDressesSortedByHighestDiscount() {

        List<Product> womensDresses = productService.getWomensDressesSortedByHighestDiscount();

        return new ResponseEntity<>(new ProductResponse(womensDresses), HttpStatus.OK);
    }
}
