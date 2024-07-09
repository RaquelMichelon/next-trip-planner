package com.rmtech.next_trip_planner.trip;

import java.util.List;

/**
 * @autor raqueldarellimichelon
 * created on 09/07/24 inside the package - com.rmtech.next_trip_planner.trip
 **/
public record TripRequestPayload(String destination, String startsAt,
                                 String endsAt, List<String> emailsToInvite, String ownerEmail, String ownerName) {
}


