package com.aldinalj.triptip.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required.")
    @Size(min = 1, max = 20, message = "Name must be between 1-20 characters")
    @Column(name = "name")
    @JsonProperty("name")
    private String displayName;

    @NotBlank(message = "You must fill in an email address.")
    @Email(message = "Email is not valid.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 30, message = "Length must be between 8-30 characters long")
    private String password;

    public CustomUser() {}

    public CustomUser(String displayName, String email, String password) {
        this.displayName = displayName;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
