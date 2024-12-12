package com.aldinalj.budget.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 20, message = "Name must be between 2-20 characters long")
    @JsonProperty("budget_name")
    private String budgetName;

    private double total;

    public Budget() {}

    public Budget(String budgetName, double total) {
        this.budgetName = budgetName;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getBudgetName() {
        return budgetName;
    }

    public void setBudgetName(String budgetName) {
        this.budgetName = budgetName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
