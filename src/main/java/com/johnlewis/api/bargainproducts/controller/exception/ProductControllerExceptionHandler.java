package com.johnlewis.api.bargainproducts.controller.exception;

import com.johnlewis.api.bargainproducts.controller.ProductController;
import com.johnlewis.api.bargainproducts.domain.Error;
import com.johnlewis.api.bargainproducts.exception.ConsumerClientException;
import com.johnlewis.api.bargainproducts.exception.ConsumerClientResponseEmptyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice(assignableTypes = {ProductController.class})
public class ProductControllerExceptionHandler {

    @ExceptionHandler(ConsumerClientResponseEmptyException.class)
    public ResponseEntity handleConsumerClientResponseEmptyException() {

        return ResponseEntity
                .status(NO_CONTENT.value())
                .build();
    }

    @ExceptionHandler(ConsumerClientException.class)
    public ResponseEntity<Error> handleConsumerClientException(ConsumerClientException ex) {

        return ResponseEntity
                .status(BAD_GATEWAY.value())
                .body(new Error(BAD_GATEWAY.value(), ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Error> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();

        return ResponseEntity
                .status(BAD_REQUEST.value())
                .body(new Error(BAD_REQUEST.value(), name + " parameter is missing"));
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Error> handleRuntimeException() {

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR.value())
                .body(new Error(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Error> handleException() {

        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR.value())
                .body(new Error(INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.getReasonPhrase()));
    }
}
