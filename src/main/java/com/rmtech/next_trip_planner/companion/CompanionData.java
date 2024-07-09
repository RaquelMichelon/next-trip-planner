package com.rmtech.next_trip_planner.companion;

import java.util.UUID;

/**
 * @autor raqueldarellimichelon
 * created on 09/07/24 inside the package - com.rmtech.next_trip_planner.companion
 **/
public record CompanionData(UUID id, String name, String email, Boolean isConfirmed) {

}
