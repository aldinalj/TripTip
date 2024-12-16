package com.aldinalj.triptip.authentication.dto;

public record AuthResponseDTO(
        String token,
        String role
) {
}
