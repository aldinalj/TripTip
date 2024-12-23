package com.aldinalj.triptip.trip.controller;

import com.aldinalj.triptip.config.security.CustomUserDetails;
import com.aldinalj.triptip.trip.model.Trip;
import com.aldinalj.triptip.trip.model.dto.TripDTO;
import com.aldinalj.triptip.trip.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/create")
    public ResponseEntity<TripDTO> createTrip(@Valid @RequestBody TripDTO tripDTO, Authentication authentication) throws Exception {

        return tripService.createTrip(tripDTO, authentication);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Trip>> getAllTrips(@AuthenticationPrincipal CustomUserDetails customUserDetails) {

        return tripService.getAllTrips(customUserDetails);
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<Trip> getTrip(@PathVariable Long tripId, @AuthenticationPrincipal CustomUserDetails userDetails) {

        return tripService.getTrip(tripId, userDetails);
    }
}
