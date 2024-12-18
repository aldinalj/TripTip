package com.aldinalj.triptip.spending.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Budget name is required.")
    @Size(min = 2, max = 20, message = "Budget name must be between 1-25 characters long.")
    @JsonProperty("budget_name")
    private String budgetName;

    public SpendingDTO() {}

    public SpendingDTO(String spendingName, String description, Double moneySpent, String budgetName) {
        this.spendingName = spendingName;
        this.description = description;
        this.moneySpent = moneySpent;
        this.budgetName = budgetName;
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

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }
}
