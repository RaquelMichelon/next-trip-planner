package com.rmtech.next_trip_planner.trip;

import com.rmtech.next_trip_planner.TripRepository;
import com.rmtech.next_trip_planner.companion.CompanionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * @autor raqueldarellimichelon
 * created on 09/07/24 inside the package - com.rmtech.next_trip_planner.trip
 **/
@RestController
@RequestMapping("trips")
public class TripController {

    @Autowired
    private CompanionService companionService;

    @Autowired
    private TripRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable UUID id) {
        Optional<Trip> trip = this.repository.findById(id);

        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody TripRequestPayload payload) {

        Trip trip = new Trip(payload);

        this.repository.save(trip);
        this.companionService.registerCompanionsToTrip(payload.emailsToInvite(), trip.getId());

        return ResponseEntity.ok(new TripCreateResponse(trip.getId()));

    }

}


