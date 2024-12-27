package com.aldinalj.triptip.activity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ActivityListDTO {

    @NotBlank(message = "You must fill in a name.")
    @Size(min = 2, max = 20, message = "Name must be between 2-20 characters long")
    @JsonProperty("name")
    private String activityListName;

    @NotNull(message = "Trip id is required.")
    @JsonProperty("trip_id")
    private Long tripId;

    public ActivityListDTO() {}

    public ActivityListDTO(String activityListName, Long tripId) {
        this.activityListName = activityListName;
        this.tripId = tripId;
    }

    public String getActivityListName() {
        return activityListName;
    }

    public void setActivityListName(String activityListName) {
        this.activityListName = activityListName;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }
}
