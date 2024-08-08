package com.rmtech.next_trip_planner.activity;

import com.rmtech.next_trip_planner.activitity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.rmtech.next_trip_planner.commons.ActivityConstant.ACTIVITY;
import static com.rmtech.next_trip_planner.commons.ActivityRequestPayloadConstant.ACTIVITY_REQUEST_PAYLOAD;
import static com.rmtech.next_trip_planner.commons.TripConstant.TRIP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @autor raqueldarellimichelon
 * created on 05/08/24 inside the package - com.rmtech.next_trip_planner.activity
 **/
@ExtendWith(MockitoExtension.class)
public class ActivityServiceTest {

    @InjectMocks
    private ActivityService service;

    @Mock
    private ActivityRepository repository;

    //operation_state_return
    @Test
    public void createActivity_WithValidData_ReturnActivityResponse() {

        //Prepare saved entity with specific id
        Activity savedActivity = ACTIVITY;
        savedActivity.setId(UUID.fromString("c1d6e821-369b-42a4-be84-d15e86612fe5"));

        // Mock repository to return the saved entity
        when(repository.save(any(Activity.class))).thenReturn(savedActivity);

        //Execute system under test
        ActivityResponse sut = service.registerActivity(ACTIVITY_REQUEST_PAYLOAD, TRIP);

        //verify that sut can not be null
        assertNotNull(sut, "sut cannot be null");
        assertEquals(savedActivity.getId(), sut.activityId());

        //Check if save method was called with the right entity
        verify(repository, times(1)).save(any(Activity.class));

    }
}


