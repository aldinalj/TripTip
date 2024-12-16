package com.aldinalj.triptip.trip.repository;

import com.aldinalj.triptip.trip.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {

}
