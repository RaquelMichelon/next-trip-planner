package com.rmtech.next_trip_planner.companion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @autor raqueldarellimichelon
 * created on 09/07/24 inside the package - com.rmtech.next_trip_planner.companion
 **/
@Repository
public interface CompanionRepository extends JpaRepository<Companion, UUID> {

    List<Companion> findByTripId(UUID id);
}
