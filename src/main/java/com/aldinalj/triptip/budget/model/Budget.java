package com.aldinalj.triptip.budget.model;

import com.aldinalj.triptip.spending.model.Spending;
import com.aldinalj.triptip.trip.model.Trip;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "budget")
public class Budget {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 20, message = "Name must be between 2-20 characters long")
    @Column(name = "name")
    @JsonProperty("name")
    private String budgetName;

    @Min(value = 0, message = "Total cannot be a negative number.")
    private Double total;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Spending> spendings = new ArrayList<>();

    public Budget() {}

    public Budget(String budgetName, Double total) {
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
