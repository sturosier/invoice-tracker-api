package com.steven.durosier.invoicetrackerapi.exception;

public class ObjectAlreadyExistsException extends  RuntimeException {

    public ObjectAlreadyExistsException(final String message) {
        super(message);
    }
}
