package com.rmtech.next_trip_planner.commons;

import com.rmtech.next_trip_planner.activitity.ActivityRequestPayload;

import java.time.LocalDateTime;

import static com.rmtech.next_trip_planner.commons.TripConstant.TRIP;

/**
 * @autor raqueldarellimichelon
 * created on 05/08/24 inside the package - com.rmtech.next_trip_planner.commons
 **/
public class ActivityRequestPayloadConstant {

    public static final ActivityRequestPayload ACTIVITY_REQUEST_PAYLOAD = new ActivityRequestPayload(
           "Visit Lagoa da Conceição",
            LocalDateTime.now().plusYears(1L).plusDays(2L).toString()
    );
}


