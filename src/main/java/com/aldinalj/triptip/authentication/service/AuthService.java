package com.aldinalj.triptip.authentication.service;

import com.aldinalj.triptip.authentication.dto.AuthResponseDTO;
import com.aldinalj.triptip.authentication.jwt.service.JwtService;
import com.aldinalj.triptip.user.model.CustomUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public AuthResponseDTO verify(CustomUserDTO customUserDTO) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                customUserDTO.username(),
                                customUserDTO.password()
                        ));

        String generatedToken = jwtService.generateToken(customUserDTO.username());
        System.out.println("Generated token: " + generatedToken);

        return new AuthResponseDTO(
                generatedToken,
                authentication.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .filter(authority -> authority.startsWith("ROLE_"))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("User has no role."))
                        .substring(5)
        );
    }
}
