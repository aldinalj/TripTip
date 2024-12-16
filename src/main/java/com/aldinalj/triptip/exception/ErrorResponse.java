package com.aldinalj.triptip.exception;

import java.time.OffsetDateTime;
import java.util.List;

public record ErrorResponse(
        int httpStatusCode,
        String message,
        OffsetDateTime timeStamp,
        List<ValidationError> validationErrors
) {

    public ErrorResponse(int httpStatusCode, String message, OffsetDateTime timeStamp) {
        this(httpStatusCode, message, timeStamp, List.of());
    }

}
