package com.aldinalj.triptip.activity.controller;

import com.aldinalj.triptip.activity.model.ActivityDTO;
import com.aldinalj.triptip.activity.model.ActivityList;
import com.aldinalj.triptip.activity.model.ActivityListDTO;
import com.aldinalj.triptip.activity.service.ActivityService;
import com.aldinalj.triptip.budget.model.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/lists/create")
    public ResponseEntity<ActivityListDTO> createActivityList(@RequestBody ActivityListDTO activityListDTO) {

        return activityService.createActivityList(activityListDTO);
    }

    @GetMapping("/lists/all")
    public ResponseEntity<List<ActivityList>> getAllActivityLists(@RequestParam ("tripName") String tripName) {

        return activityService.getAllActivityLists(tripName);
    }

    @PostMapping("/create")
    public ResponseEntity<ActivityDTO> createActivity(@RequestBody ActivityDTO activityDTO) {

        return activityService.createActivity(activityDTO);
    }


    
}
