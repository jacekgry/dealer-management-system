package com.jacekgry.cardealership.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.CONFLICT)
public class DeletionException extends Exception {
    private Map<String, String> associatedEntities;
    private String entityName;
    private int id;

    public DeletionException(String entityName, int id, Map<String, String> associatedEntities) {
        super();
        this.entityName = entityName;
        this.associatedEntities = associatedEntities;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Cannot delete " + this.entityName + " of id " + id + " because it is associated with "
                + associatedEntities
                .entrySet()
                .stream()
                .filter(entry -> !"[]".equals(entry.getValue()))
                .map(entry -> entry.getKey() + "(s) of id(s) " + entry.getValue())
                .reduce((a, b) -> String.join(";", a, b))
                .orElse("");
    }
}
