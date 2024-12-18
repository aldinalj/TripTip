package com.aldinalj.triptip.activity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ActivityListDTO {

    @NotBlank(message = "You must fill in a name.")
    @Size(min = 2, max = 20, message = "Name must be between 2-20 characters long")
    @JsonProperty("name")
    private String activityListName;

    @NotBlank(message = "Trip name is required.")
    @Size(min = 1, max = 25, message = "Trip name must be between 1-25 characters long.")
    @JsonProperty("trip_name")
    private String tripName;

    public ActivityListDTO() {}

    public ActivityListDTO(String activityName, String tripName) {
        this.activityListName = activityName;
        this.tripName = tripName;
    }

    public String getActivityName() {
        return activityListName;
    }

    public void setActivityName(String activityName) {
        this.activityListName = activityName;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getActivityListName() {
        return activityListName;
    }

    public void setActivityListName(String activityListName) {
        this.activityListName = activityListName;
    }
}
