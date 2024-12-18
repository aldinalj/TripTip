package com.aldinalj.triptip.activity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ActivityDTO {

    @Size(min = 2, max = 20, message = "Name must be between 2-20 characters long")
    @JsonProperty("name")
    private String activityName;

    @Min(value = 0, message = "Minimum price cannot be negative.")
    @JsonProperty("price_min")
    private Double priceMin;

    @Min(value = 0, message = "Maximum price cannot be negative.")
    @JsonProperty("price_max")
    private Double priceMax;

    @NotBlank(message = "You must fill in a name.")
    @Size(min = 2, max = 20, message = "Name must be between 2-20 characters long")
    @JsonProperty("list_name")
    private String activityListName;

    public ActivityDTO() {}

    public ActivityDTO(String activityName, Double priceMin, Double priceMax, String activityListName) {
        this.activityName = activityName;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.activityListName = activityListName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public String getActivityListName() {
        return activityListName;
    }

    public void setActivityListName(String activityListName) {
        this.activityListName = activityListName;
    }
}
