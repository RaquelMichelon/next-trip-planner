package com.rmtech.next_trip_planner.activitity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @autor raqueldarellimichelon
 * created on 15/07/24 inside the package - com.rmtech.next_trip_planner.activitity
 **/
public record ActivityData(UUID id, String title, LocalDateTime occursAt) {
}


