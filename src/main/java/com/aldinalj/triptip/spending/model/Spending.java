package com.aldinalj.triptip.spending.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "spending")
public class Spending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 40)
    @Column(name = "name")
    @JsonProperty("name")
    String spendingName;

    @Max(value = 300, message = "Description can have a max length of 300 characters.")
    @JsonProperty("desc")
    String description;

    @Column(name = "money_spent")
    @JsonProperty("money_spent")
    @Min(value = 0, message = "Value cannot be 0.")
    private double moneySpent;

    public Spending() {}

    public Spending(String spendingName, String description, double moneySpent) {
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

    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }
}
