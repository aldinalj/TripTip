package com.aldinalj.triptip.budget.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BudgetDTO {

    @NotBlank(message = "Budget name is required.")
    @Size(min = 2, max = 20, message = "Name must be between 2-20 characters long")
    @JsonProperty("name")
    private String budgetName;

    @Min(value = 0, message = "Total cannot be a negative number.")
    private Double total;

    @NotNull(message = "Trip id is required.")
    @JsonProperty("trip_id")
    private Long tripId;

    public BudgetDTO() {}

    public BudgetDTO(String budgetName, Double total, Long tripId) {
        this.budgetName = budgetName;
        this.total = total;
        this.tripId = tripId;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
}
