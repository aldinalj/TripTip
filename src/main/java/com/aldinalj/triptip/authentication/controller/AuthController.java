package com.aldinalj.triptip.authentication.controller;

import com.aldinalj.triptip.authentication.dto.AuthResponseDTO;
import com.aldinalj.triptip.authentication.service.AuthService;
import com.aldinalj.triptip.user.model.CustomUserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@RequestBody CustomUserDTO customUserDTO) {

        return ResponseEntity.ok(authService.verify(customUserDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<CustomUserDTO> register(@Valid @RequestBody CustomUserDTO user) {

        return authService.createUser(user);
    }
}
