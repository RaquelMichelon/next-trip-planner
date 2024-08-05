package com.rmtech.next_trip_planner.commons;

import com.rmtech.next_trip_planner.trip.TripRequestPayload;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @autor raqueldarellimichelon
 * created on 05/08/24 inside the package - com.rmtech.next_trip_planner.commons
 **/
public class TripRequestPayloadConstant {
    public static final TripRequestPayload TRIP_REQUEST_PAYLOAD = new TripRequestPayload(
            "Florianópolis - SC - Brazil",
            LocalDateTime.now().plusYears(1L).toString(),
            LocalDateTime.now().plusYears(1L).plusMonths(1L).toString(),
            List.of("companion1@email.test.com", "companion2@email.test.com", "companion3@email.test.com"),
            "owner@email.test.com",
            "Owner Name Test"
    );
}


