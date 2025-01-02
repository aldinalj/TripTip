package com.aldinalj.triptip.trip.repository;

import com.aldinalj.triptip.trip.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TripRepository extends JpaRepository<Trip, Integer> {

    List<Trip> findByUserId(Long userId);

    Optional<Trip> findByIdAndUserId(Long tripId, Long userId);

    @Query("SELECT t FROM Trip t WHERE t.id = :tripId AND t.user.id = :userId")
    Optional<Trip> findByTripIdAndUserId(@Param("tripId") Long tripId, @Param("userId") Long userId);

}
