package com.aldinalj.triptip.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CustomUserDTO(

        @NotBlank(message = "Display name is required.")
        @Size(min = 1, max = 20, message = "Display name must be between 1-20 characters")
        @JsonProperty("display_name")
        String displayName,

        @NotBlank(message = "Username is required")
        @Size(min = 1, max = 20, message = "Username must be between 1-20 characters")
        String username,

        @NotBlank(message = "Password is required.")
        @Size(min = 8, max = 30, message = "Length must be between 8-30 characters long")
        String password
) {
    public CustomUserDTO(String displayName, String username) {
        this(displayName, username, "");
    }
}
