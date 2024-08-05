package com.rmtech.next_trip_planner.commons;

import com.rmtech.next_trip_planner.trip.Trip;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.rmtech.next_trip_planner.commons.TripRequestPayloadConstant.TRIP_REQUEST_PAYLOAD;

/**
 * @autor raqueldarellimichelon
 * created on 05/08/24 inside the package - com.rmtech.next_trip_planner.commons
 **/
public class TripConstant {
    public static final Trip TRIP = new Trip(
            TRIP_REQUEST_PAYLOAD
    );

}


