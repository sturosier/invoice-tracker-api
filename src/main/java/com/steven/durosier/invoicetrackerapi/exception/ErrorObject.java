package com.steven.durosier.invoicetrackerapi.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.OffsetDateTime;

@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private OffsetDateTime timestamp;

    public static ResponseEntity<ErrorObject> toResponseEntity(final String message, final HttpStatus status)
    {
        final ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(status.value());
        errorObject.setMessage(message);
        errorObject.setTimestamp(OffsetDateTime.now());

        return new ResponseEntity<>(errorObject, status);
    }
}
