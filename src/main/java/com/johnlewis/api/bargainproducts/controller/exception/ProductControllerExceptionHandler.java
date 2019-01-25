package com.johnlewis.api.bargainproducts.controller.exception;

import com.johnlewis.api.bargainproducts.controller.ProductController;
import com.johnlewis.api.bargainproducts.domain.Error;
import com.johnlewis.api.bargainproducts.exception.ConsumerClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice(assignableTypes = {ProductController.class})
public class ProductControllerExceptionHandler {

    @ExceptionHandler(ConsumerClientException.class)
    public ResponseEntity<Error> handleConsumerClientException(ConsumerClientException ex) {

        return ResponseEntity
                .status(BAD_GATEWAY.value())
                .body(new Error(BAD_GATEWAY.value(), ex.getMessage()));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Error> handleRuntimeException() {

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR.value())
                .body(new Error(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }
}
