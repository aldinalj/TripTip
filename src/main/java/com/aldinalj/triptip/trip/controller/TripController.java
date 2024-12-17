package com.aldinalj.triptip.trip.controller;

import com.aldinalj.triptip.trip.model.dto.TripDTO;
import com.aldinalj.triptip.trip.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping("/create")
    public ResponseEntity<TripDTO> createTrip(@Valid @RequestBody TripDTO tripDTO, Authentication authentication) throws Exception {


        System.out.println("USER NAME FROM AUTHENTICATION: "+ authentication.getName());

        return tripService.createTrip(tripDTO, authentication);
    }
}
