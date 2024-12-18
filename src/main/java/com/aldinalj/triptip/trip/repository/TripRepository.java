package com.aldinalj.triptip.trip.repository;

import com.aldinalj.triptip.trip.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Integer> {

    Optional<Trip> findByTripNameIgnoreCase(String tripName);

}
