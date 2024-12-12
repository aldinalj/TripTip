package com.aldinalj.triptip.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 20, message = "Length must be between 1-20 characters")
    private String display_name;

    @NotBlank(message = "Email is not valid.")
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 30, message = "Length must be between 8-30 characters long")
    private String password;

    public CustomUser() {}

    public CustomUser(String display_name, String email, String password) {
        this.display_name = display_name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
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
