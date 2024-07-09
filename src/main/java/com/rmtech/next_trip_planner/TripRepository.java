package com.rmtech.next_trip_planner;

import com.rmtech.next_trip_planner.trip.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @autor raqueldarellimichelon
 * created on 09/07/24 inside the package - com.rmtech.next_trip_planner
 **/
public interface TripRepository extends JpaRepository<Trip, UUID> {
}
