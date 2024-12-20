package com.aldinalj.triptip.activity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 20, message = "Name must be between 2-20 characters long")
    @Column(name = "name")
    @JsonProperty("name")
    private String activityName;

    @Min(value = 0, message = "Minimum price cannot be negative.")
    @JsonProperty("price_min")
    @Column(name = "price_min")
    private Double priceMin;

    @Min(value = 0, message = "Maximum price cannot be negative.")
    @JsonProperty("price_max")
    @Column(name = "price_max")
    private Double priceMax;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "activity_list_id", nullable = false)
    private ActivityList activityList;

    public Activity() {}

    public Activity(String activityName, Double priceMin, Double priceMax) {
        this.activityName = activityName;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    public Long getId() {
        return id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    public double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public ActivityList getActivityList() {
        return activityList;
    }

    public void setActivityList(ActivityList activityList) {
        this.activityList = activityList;
    }
}
