package com.aldinalj.triptip.budget.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BudgetDTO {

    @NotBlank(message = "Budget name is required.")
    @Size(min = 2, max = 20, message = "Name must be between 2-20 characters long")
    @JsonProperty("name")
    private String budgetName;

    @Min(value = 0, message = "Total cannot be a negative number.")
    private Double total;

    @NotBlank(message = "Trip name is required.")
    @Size(min = 1, max = 25, message = "Trip name must be between 1-25 characters long.")
    @JsonProperty("trip_name")
    private String tripName;

    public BudgetDTO() {}

    public BudgetDTO(String budgetName, Double total, String tripName) {
        this.budgetName = budgetName;
        this.total = total;
        this.tripName = tripName;
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

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }
}
