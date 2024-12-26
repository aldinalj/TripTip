package com.aldinalj.triptip.activity.service;

import com.aldinalj.triptip.activity.model.ActivityList;
import com.aldinalj.triptip.activity.model.ActivityListDTO;
import com.aldinalj.triptip.activity.reporitory.ActivityListRepository;
import com.aldinalj.triptip.config.security.CustomUserDetails;
import com.aldinalj.triptip.trip.model.Trip;
import com.aldinalj.triptip.trip.repository.TripRepository;
import com.aldinalj.triptip.user.model.CustomUser;
import com.aldinalj.triptip.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityListService {

    private final ActivityListRepository activityListRepository;
    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    @Autowired
    public ActivityListService(ActivityListRepository activityListRepository, TripRepository tripRepository, UserRepository userRepository) {
        this.activityListRepository = activityListRepository;
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
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
    public ResponseEntity<List<ActivityList>> getActivityListsByTrip(Long tripId, CustomUserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Trip trip = tripRepository.findByIdAndUserId(tripId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        List<ActivityList> activityLists = activityListRepository.findAllByTripId(trip.getId());

        return ResponseEntity.ok(activityLists);
    }

    @Transactional
    public ResponseEntity<ActivityList> getActivityList(Long activityListId, Long tripId, CustomUserDetails userDetails) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Trip trip = tripRepository.findByIdAndUserId(tripId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        ActivityList activityList = activityListRepository.findByIdAndTripId(activityListId, trip.getId())
                .orElseThrow(() -> new IllegalArgumentException("Activity list not found"));

        return ResponseEntity.ok(activityList);
    }

    @Transactional
    public ResponseEntity<List<ActivityList>> getAllLists(CustomUserDetails userDetails) {

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<ActivityList> lists = activityListRepository.findAllLists(user.getId());

        return ResponseEntity.ok(lists);
    }
}
