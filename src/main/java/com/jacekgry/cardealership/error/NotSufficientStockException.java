package com.jacekgry.cardealership.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotSufficientStockException extends RuntimeException {
    public NotSufficientStockException(String cd, String car) {
        super("Not sufficient stock in car dealership " + cd + " to process purchase of car " + car);
    }
}
