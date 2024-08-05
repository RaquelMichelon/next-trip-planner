package com.rmtech.next_trip_planner.activity;

import com.rmtech.next_trip_planner.activitity.Activity;
import com.rmtech.next_trip_planner.activitity.ActivityRepository;
import com.rmtech.next_trip_planner.activitity.ActivityResponse;
import com.rmtech.next_trip_planner.activitity.ActivityService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        when(repository.save(any(Activity.class))).thenReturn(ACTIVITY);
        ActivityResponse sut = service.registerActivity(ACTIVITY_REQUEST_PAYLOAD, TRIP);

        assertThat(sut).isNotNull();

//        ActivityResponse sut = service.registerActivity(ACTIVITY_REQUEST_PAYLOAD, TRIP);
//        verify(repository).save(any(Activity.class));
//        assertThat(sut).isNotNull();
    }
}


