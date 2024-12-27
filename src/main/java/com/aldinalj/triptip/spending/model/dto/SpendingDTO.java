package com.aldinalj.triptip.spending.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SpendingDTO {

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 2, max = 40, message = "Name must be between 2-40 characters.")
    @JsonProperty("name")
    private String spendingName;

    @Size(max = 300, message = "Description can have a max length of 300 characters.")
    @JsonProperty("desc")
    private String description;

    @Min(value = 0, message = "Value cannot be a negative number.")
    @JsonProperty("money_spent")
    private Double moneySpent;

    @NotNull(message = "Budget id is required.")
    @JsonProperty("budget_id")
    private Long budgetId;

    public SpendingDTO() {}

    public SpendingDTO(String spendingName, String description, Double moneySpent, Long budgetId) {
        this.spendingName = spendingName;
        this.description = description;
        this.moneySpent = moneySpent;
        this.budgetId = budgetId;
    }

    public String getSpendingName() {
        return spendingName;
    }

    public void setSpendingName(String spendingName) {
        this.spendingName = spendingName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(Double moneySpent) {
        this.moneySpent = moneySpent;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }
}
