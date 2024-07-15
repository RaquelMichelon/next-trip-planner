package com.rmtech.next_trip_planner.activitity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @autor raqueldarellimichelon
 * created on 15/07/24 inside the package - com.rmtech.next_trip_planner.activitity
 **/
public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    List<Activity> findByTripId(UUID tripId);
}
