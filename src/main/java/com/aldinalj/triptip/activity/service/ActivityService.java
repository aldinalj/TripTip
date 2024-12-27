package com.aldinalj.triptip.activity.service;

import com.aldinalj.triptip.activity.model.Activity;
import com.aldinalj.triptip.activity.model.ActivityDTO;
import com.aldinalj.triptip.activity.model.ActivityList;
import com.aldinalj.triptip.activity.reporitory.ActivityListRepository;
import com.aldinalj.triptip.activity.reporitory.ActivityRepository;
import com.aldinalj.triptip.config.security.CustomUserDetails;
import com.aldinalj.triptip.user.model.CustomUser;
import com.aldinalj.triptip.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActivityService {

    private final ActivityListRepository activityListRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    @Autowired
    public ActivityService(ActivityListRepository activityListRepository, ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityListRepository = activityListRepository;
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity<ActivityDTO> createActivity(ActivityDTO activityDTO, CustomUserDetails userDetails) {

        if (activityDTO.getPriceMax() < activityDTO.getPriceMin()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(activityDTO);
        }

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        ActivityList activityList = activityListRepository.findByListIdAndUserId(activityDTO.getListId(), user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Activity list not found"));

        Activity activity = new Activity(
                activityDTO.getActivityName(),
                activityDTO.getPriceMin(),
                activityDTO.getPriceMax()
        );

        activity.setActivityList(activityList);
        activityRepository.save(activity);

        return ResponseEntity.status(HttpStatus.CREATED).body(activityDTO);
    }

    @Transactional
    public ResponseEntity<List<Activity>> getAllActivitiesByList(
            Long activityListId,
            CustomUserDetails userDetails
    ) {

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Activity> activities = activityRepository.findAllByActivityListIdAndUserId(activityListId, user.getId());

        return ResponseEntity.ok(activities);
    }

    @Transactional
    public ResponseEntity<Activity> getActivity(Long activityId, Long activityListId, CustomUserDetails userDetails) {

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Activity activity = activityRepository.findByIdAndActivityListIdAndUserId(activityId, activityListId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Activity not found"));

        return ResponseEntity.ok(activity);
    }

    @Transactional
    public ResponseEntity<List<Activity>> getAllActivities(CustomUserDetails userDetails) {

        CustomUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Activity> activities = activityRepository.findAllActivities(user.getId());

        return ResponseEntity.ok(activities);
    }


}
