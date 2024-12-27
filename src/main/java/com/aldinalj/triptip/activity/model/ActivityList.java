package com.aldinalj.triptip.activity.model;

import com.aldinalj.triptip.trip.model.Trip;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activity_list")
public class ActivityList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "You must fill in a name.")
    @Size(min = 2, max = 20, message = "Name must be between 2-20 characters long")
    @Column(name = "name")
    @JsonProperty("name")
    private String activityListName;

    @NotNull(message = "Trip id is required.")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @OneToMany(mappedBy = "activityList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activity> activities = new ArrayList<>();


    public ActivityList() {}

    public ActivityList(String activityListName) {
        this.activityListName = activityListName;
    }

    public Long getId() {
        return id;
    }

    public String getActivityListName() {
        return activityListName;
    }

    public void setActivityListName(String activityListName) {
        this.activityListName = activityListName;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}