package com.steven.durosier.invoicetrackerapi.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @NotNull(message = "Please enter an email")
    //@Email(message = "Please enter a valid email")
    private String email;

    @NotNull(message = "Please enter a password")
    @Size(min = 5, message = "Password must be at least 5 characters")
    private String password;
}
