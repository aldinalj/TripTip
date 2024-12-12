package com.aldinalj.triptip.activity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

public class ActivityList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 20, message = "Name must be between 2-20 characters long")
    @Column(name = "name")
    @JsonProperty("name")
    private String activityName;

    public ActivityList() {}

    public ActivityList(String activityName) {
        this.activityName = activityName;
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
}