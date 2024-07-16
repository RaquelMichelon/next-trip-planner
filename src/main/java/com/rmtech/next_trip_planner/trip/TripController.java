package com.rmtech.next_trip_planner.trip;

import com.rmtech.next_trip_planner.activitity.ActivityData;
import com.rmtech.next_trip_planner.activitity.ActivityRequestPayload;
import com.rmtech.next_trip_planner.activitity.ActivityResponse;
import com.rmtech.next_trip_planner.activitity.ActivityService;
import com.rmtech.next_trip_planner.companion.*;
import com.rmtech.next_trip_planner.link.LinkData;
import com.rmtech.next_trip_planner.link.LinkRequestPayload;
import com.rmtech.next_trip_planner.link.LinkResponse;
import com.rmtech.next_trip_planner.link.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private ActivityService activityService;

    @Autowired
    private LinkService linkService;

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable UUID id) {
        Optional<Trip> trip = this.repository.findById(id);

        return trip.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody TripRequestPayload payload) {

        Trip trip = new Trip(payload);

        //check if init date is before end date
        if(!trip.getStartsAt().isBefore(trip.getEndsAt())) {
            throw new InvalidEndDateException("Invalid end date. The value must be after the start date.");
        }

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

    @PostMapping("/{id}/activities")
    public ResponseEntity<ActivityResponse> registerActivity(@PathVariable UUID id, @RequestBody ActivityRequestPayload payload) {

        Optional<Trip> trip = this.repository.findById(id);

        if(trip.isPresent()){
            Trip rawTrip = trip.get();

            ActivityResponse activityResponse = this.activityService.registerActivity(payload, rawTrip);

            return ResponseEntity.ok(activityResponse);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}/activities")
    public ResponseEntity<List<ActivityData>> getAllActivities(@PathVariable UUID id){
        List<ActivityData> activityDataList = this.activityService.getAllActivitiesFromId(id);

        return ResponseEntity.ok(activityDataList);
    }

    @PostMapping("/{id}/links")
    public ResponseEntity<LinkResponse> registerLink(@PathVariable UUID id, @RequestBody LinkRequestPayload payload) {

        Optional<Trip> trip = this.repository.findById(id);

        if(trip.isPresent()){
            Trip rawTrip = trip.get();

            LinkResponse linkResponse = this.linkService.registerLink(payload, rawTrip);

            return ResponseEntity.ok(linkResponse);
        }
        return ResponseEntity.notFound().build();

    }

    @GetMapping("/{id}/links")
    public ResponseEntity<List<LinkData>> getAllLinks(@PathVariable UUID id){
        List<LinkData> linkDataList = this.linkService.getAllLinksFromTrip(id);

        return ResponseEntity.ok(linkDataList);
    }

}


