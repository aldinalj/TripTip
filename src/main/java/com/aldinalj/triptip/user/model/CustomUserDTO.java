package com.aldinalj.triptip.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomUserDTO(

        @NotBlank(message = "Name is required.")
        @Size(min = 1, max = 20, message = "Name must be between 1-20 characters")
        @JsonProperty("name")
        String displayName,

        @NotBlank(message = "You must fill in an email address.")
        @Email(message = "Email is not valid.")
        String email,

        @NotBlank(message = "Password is required.")
        @Size(min = 8, max = 30, message = "Length must be between 8-30 characters long")
        String password
) {
    public CustomUserDTO(String displayName, String email) {
        this(displayName, email, "");
    }
}
