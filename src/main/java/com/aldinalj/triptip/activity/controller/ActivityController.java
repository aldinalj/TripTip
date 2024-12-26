package com.aldinalj.triptip.activity.controller;

import com.aldinalj.triptip.activity.model.Activity;
import com.aldinalj.triptip.activity.model.ActivityDTO;
import com.aldinalj.triptip.activity.service.ActivityService;
import com.aldinalj.triptip.config.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/create")
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {

        return activityService.createActivity(activityDTO);
    }

    @GetMapping("/{activityListId}")
    public ResponseEntity<List<Activity>> getAllActivities(
            @PathVariable Long activityListId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        return activityService.getAllActivitiesByList(activityListId, userDetails);
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Activity> getActivity(
            @PathVariable Long activityId,
            @RequestParam Long activityListId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return activityService.getActivity(activityId, activityListId, customUserDetails);
    }

    @GetMapping
    public ResponseEntity<List<Activity>> getAllActivities(@AuthenticationPrincipal CustomUserDetails userDetails) {

        return activityService.getAllActivities(userDetails);
    }

}
