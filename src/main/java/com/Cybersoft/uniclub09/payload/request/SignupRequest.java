package com.Cybersoft.uniclub09.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupRequest {
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "Password cannot be null")
    private String password;
    private String fullName;
}
