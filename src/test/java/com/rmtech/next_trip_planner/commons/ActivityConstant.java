package com.rmtech.next_trip_planner.commons;

import com.rmtech.next_trip_planner.activitity.Activity;

import java.time.LocalDateTime;

import static com.rmtech.next_trip_planner.commons.ActivityRequestPayloadConstant.ACTIVITY_REQUEST_PAYLOAD;
import static com.rmtech.next_trip_planner.commons.TripConstant.TRIP;

/**
 * @autor raqueldarellimichelon
 * created on 05/08/24 inside the package - com.rmtech.next_trip_planner.commons
 **/
public class ActivityConstant {

    public static final Activity ACTIVITY = new Activity(
            ACTIVITY_REQUEST_PAYLOAD.title(),
            ACTIVITY_REQUEST_PAYLOAD.occursAt(),
            TRIP);
}


