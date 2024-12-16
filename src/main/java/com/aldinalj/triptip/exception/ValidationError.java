package com.aldinalj.triptip.exception;

public record ValidationError(
        String field,
        String error
) {
}
