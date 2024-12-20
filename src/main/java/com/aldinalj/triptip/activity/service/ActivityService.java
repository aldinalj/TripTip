package com.aldinalj.triptip.activity.service;

import com.aldinalj.triptip.activity.model.Activity;
import com.aldinalj.triptip.activity.model.ActivityDTO;
import com.aldinalj.triptip.activity.model.ActivityList;
import com.aldinalj.triptip.activity.model.ActivityListDTO;
import com.aldinalj.triptip.activity.reporitory.ActivityListRepository;
import com.aldinalj.triptip.activity.reporitory.ActivityRepository;
import com.aldinalj.triptip.budget.model.Budget;
import com.aldinalj.triptip.trip.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityListRepository activityListRepository;
    private final TripRepository tripRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityService(ActivityListRepository activityListRepository, TripRepository tripRepository, ActivityRepository activityRepository) {
        this.activityListRepository = activityListRepository;
        this.tripRepository = tripRepository;
        this.activityRepository = activityRepository;
    }


    @Transactional
    public ResponseEntity<ActivityListDTO> createActivityList(ActivityListDTO activityListDTO) {

        if (activityListRepository.findByActivityListNameIgnoreCase(activityListDTO.getActivityListName()).isPresent()) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        ActivityList activityList = new ActivityList(
                activityListDTO.getActivityListName()
        );

        activityList.setTrip(tripRepository.findByTripNameIgnoreCase(activityListDTO.getTripName())
        .orElseThrow(() -> new IllegalArgumentException("Trip not found")));

        activityListRepository.save(activityList);

        return ResponseEntity.status(HttpStatus.CREATED).body(activityListDTO);
    }

    @Transactional
    public ResponseEntity<List<ActivityList>> getAllActivityLists(String tripName) {

        Long tripId = tripRepository.findIdByTripName(tripName)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        List<ActivityList> activityLists = activityListRepository.findByTripId(tripId);

        return ResponseEntity.ok(activityLists);
    }

    @Transactional
    public ResponseEntity<ActivityDTO> createActivity(ActivityDTO activityDTO) {

        if (activityRepository.findByActivityNameIgnoreCase(activityDTO.getActivityName()).isPresent()) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        if (activityDTO.getPriceMax() < activityDTO.getPriceMin()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(activityDTO);
        }

        Activity activity = new Activity(
                activityDTO.getActivityName(),
                activityDTO.getPriceMin(),
                activityDTO.getPriceMax()
        );

        activity.setActivityList(activityListRepository.findByActivityListNameIgnoreCase(activityDTO.getActivityListName())
        .orElseThrow(() -> new IllegalArgumentException("Activity list not found")));

        activityRepository.save(activity);

        return ResponseEntity.status(HttpStatus.CREATED).body(activityDTO);
    }

    @Transactional
    public ResponseEntity<List<Activity>> getAllActivities(String activityListName) {

        Long activityListId = activityListRepository.findIdByActivityListActivityListName(activityListName)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        List<Activity> activities = activityRepository.findByActivityListId(activityListId);

        return ResponseEntity.ok(activities);
    }
}
