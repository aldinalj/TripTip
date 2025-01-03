package com.aldinalj.triptip.trip.service;

import com.aldinalj.triptip.config.security.CustomUserDetails;
import com.aldinalj.triptip.trip.model.Trip;
import com.aldinalj.triptip.trip.model.dto.TripDTO;
import com.aldinalj.triptip.trip.repository.TripRepository;
import com.aldinalj.triptip.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;

    @Autowired
    public TripService(TripRepository tripRepository, UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity<TripDTO> createTrip(TripDTO tripDTO, Authentication authentication) {

        Trip trip = new Trip(
                tripDTO.getTripName(),
                tripDTO.getCountry(),
                tripDTO.getStartDate(),
                tripDTO.getEndDate()
        );

        if (authentication != null && authentication.isAuthenticated() ) {

            trip.setUser(userRepository.findByUsername(authentication.getName())
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found")));

        } else {

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }

        tripRepository.save(trip);

        return ResponseEntity.status(HttpStatus.CREATED).body(tripDTO);
    }


    @Transactional
    public ResponseEntity<List<Trip>> getAllTrips(@AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userRepository.findIdByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<Trip> trips = tripRepository.findByUserId(userId);

        return ResponseEntity.ok(trips);
    }

    @Transactional
    public ResponseEntity<Trip> getTrip(Long tripId, CustomUserDetails userDetails) {

        Long userId = userRepository.findIdByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Trip trip = tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        return ResponseEntity.ok(trip);
    }
}