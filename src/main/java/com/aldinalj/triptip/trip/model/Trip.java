package com.aldinalj.triptip.trip.model;

import com.aldinalj.triptip.budget.model.Budget;
import com.aldinalj.triptip.user.model.CustomUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Trip name is required.")
    @Size(min = 1, max = 25, message = "Trip name must be between 1-25 characters long.")
    @Column(name = "name")
    @JsonProperty("name")
    private String tripName;

    @Size(max = 40, message = "Country name cannot be more than 40 characters.")
    private String country;

    @Column(name = "start_date")
    @JsonProperty("start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    @JsonProperty("end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    private CustomUser user;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Budget> budgets = new ArrayList<>();

    public Trip() {}

    public Trip(String tripName, String country, LocalDate startDate, LocalDate endDate) {
        this.tripName = tripName;
        this.country = country;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }
}


