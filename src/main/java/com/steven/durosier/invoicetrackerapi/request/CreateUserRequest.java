package com.steven.durosier.invoicetrackerapi.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Please enter a name")
    private String name;

    private Long age = 0L;

    @NotNull(message = "Please enter an email")
    @Email(message = "Please enter a valid email")
    private String email;

    @NotNull(message = "Please enter a password")
    @Size(min = 5, message = "Password must be at least 5 characters")
    private String password;
}
