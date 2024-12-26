package com.aldinalj.triptip.activity.controller;

import com.aldinalj.triptip.activity.model.ActivityList;
import com.aldinalj.triptip.activity.model.ActivityListDTO;
import com.aldinalj.triptip.activity.service.ActivityListService;
import com.aldinalj.triptip.config.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities/lists")
public class ActivityListController {

    private final ActivityListService activityListService;

    public ActivityListController(ActivityListService activityListService) {
        this.activityListService = activityListService;
    }

    @PostMapping("/create")
    public ResponseEntity<ActivityListDTO> createActivityList(@RequestBody ActivityListDTO activityListDTO) {

        return activityListService.createActivityList(activityListDTO);
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<List<ActivityList>> getActivityListsByTrip(
            @PathVariable Long tripId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        return activityListService.getActivityListsByTrip(tripId, userDetails);
    }

    @GetMapping("/list/{activityListId}")
    public ResponseEntity<ActivityList> getActivityListById(
            @PathVariable Long activityListId,
            @RequestParam Long tripId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return activityListService.getActivityList(activityListId, tripId, customUserDetails);
    }

    @GetMapping
    public ResponseEntity<List<ActivityList>> getAllLists(@AuthenticationPrincipal CustomUserDetails userDetails) {

        return activityListService.getAllLists(userDetails);
    }
}
