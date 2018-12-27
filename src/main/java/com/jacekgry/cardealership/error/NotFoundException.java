package com.jacekgry.cardealership.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String entityName, Integer id) {
        super(entityName + " with ID " + id + " does not exist.");
    }
}