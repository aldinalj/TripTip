package com.aldinalj.triptip.spending.model;

import com.aldinalj.triptip.budget.model.Budget;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "spending")
public class Spending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 2, max = 40, message = "Name must be between 2-40 characters.")
    @Column(name = "name")
    @JsonProperty("name")
    private String spendingName;

    @Size(max = 300, message = "Description can have a max length of 300 characters.")
    @JsonProperty("desc")
    private String description;

    @Min(value = 0, message = "Value cannot be a negative number.")
    @Column(name = "money_spent")
    @JsonProperty("money_spent")
    private Double moneySpent;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "budget_id", nullable = false)
    private Budget budget;

    public Spending() {}

    public Spending(String spendingName, String description, Double moneySpent) {
        this.spendingName = spendingName;
        this.description = description;
        this.moneySpent = moneySpent;
    }

    public Long getId() {
        return id;
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

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
