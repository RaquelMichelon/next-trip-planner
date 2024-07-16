package com.rmtech.next_trip_planner.link;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @autor raqueldarellimichelon
 * created on 15/07/24 inside the package - com.rmtech.next_trip_planner.link
 **/
public interface LinkRepository extends JpaRepository<Link, UUID> {
    public List<Link> findByTripId(UUID tripId);
}
