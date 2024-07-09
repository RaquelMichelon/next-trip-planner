package com.rmtech.next_trip_planner.trip;

import com.rmtech.next_trip_planner.TripRepository;
import com.rmtech.next_trip_planner.companion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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
        this.companionService.registerCompanionsToTrip(payload.emailsToInvite(), trip);

        return ResponseEntity.ok(new TripCreateResponse(trip.getId()));

    }


    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID id, @RequestBody TripRequestPayload payload) {
        Optional<Trip> trip = this.repository.findById(id);

        //trip exists?
        if(trip.isPresent()) {
            Trip rawTrip = trip.get();
            rawTrip.setStartsAt(LocalDateTime.parse(payload.startsAt(), DateTimeFormatter.ISO_DATE_TIME));
            rawTrip.setEndsAt(LocalDateTime.parse(payload.endsAt(), DateTimeFormatter.ISO_DATE_TIME));
            rawTrip.setDestination(payload.destination());

            this.repository.save(rawTrip);

            return ResponseEntity.ok(rawTrip);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/confirm")
    public ResponseEntity<Trip> confirmTrip(@PathVariable UUID id) {
        Optional<Trip> trip = this.repository.findById(id);

        //trip exists?
        if(trip.isPresent()) {
            Trip rawTrip = trip.get();
            rawTrip.setIsConfirmed(true);

            this.repository.save(rawTrip);
            this.companionService.triggerConfirmationEmailToCompanions(id);

            return ResponseEntity.ok(rawTrip);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/invite ")
    public ResponseEntity<CompanionCreateResponse> inviteCompanion(@PathVariable UUID id, @RequestBody CompanionRequestPayload payload) {

        Optional<Trip> trip = this.repository.findById(id);

        //trip exists?
        if(trip.isPresent()) {
            Trip rawTrip = trip.get();

            CompanionCreateResponse companionResponse = this.companionService.registerCompanionToTrip(payload.email(), rawTrip);

            if(rawTrip.getIsConfirmed()) this.companionService.triggerConfirmationEmailToCompanion(payload.email());

            return ResponseEntity.ok(companionResponse);
        }

        return ResponseEntity.notFound().build();

    }


    @GetMapping("/{id}/companions")
    public ResponseEntity<List<CompanionData>> getAllCompanions(@PathVariable UUID id) {
        List<CompanionData> companiosList = this.companionService.getAllCompanionsFromTrip(id);
        return ResponseEntity.ok(companiosList);
    }

}


