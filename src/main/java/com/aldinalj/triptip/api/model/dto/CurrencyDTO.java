package com.aldinalj.triptip.api.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CurrencyDTO {

    @NotNull(message = "Source currency is required.")
    @Size(min = 3, max = 3, message = "Currency code must be 3 characters.")
    private String from;

    @NotNull(message = "Target currency is required.")
    @Size(min = 3, max = 3, message = "Currency code must be 3 characters.")
    private String to;

    @NotNull(message = "Amount is required.")
    @Positive(message = "Amount must be greater than zero.")
    private Double amount;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
