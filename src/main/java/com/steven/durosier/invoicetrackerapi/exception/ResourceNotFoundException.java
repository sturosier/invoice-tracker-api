package com.steven.durosier.invoicetrackerapi.exception;

public class ResourceNotFoundException extends  RuntimeException {

    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
