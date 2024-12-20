package com.aldinalj.triptip.trip.repository;

import com.aldinalj.triptip.trip.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Integer> {


    Optional<Trip> findByTripNameIgnoreCase(String tripName);

    List<Trip> findByUserId(Long userId);

    @Query("SELECT t.id FROM Trip t WHERE LOWER(t.tripName) = LOWER(:tripName)")
    Optional<Long> findIdByTripName(String tripName);

}
