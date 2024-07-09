package com.rmtech.next_trip_planner.companion;

import com.rmtech.next_trip_planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @autor raqueldarellimichelon
 * created on 09/07/24 inside the package - com.rmtech.next_trip_planner.companion
 **/
@Service
public class CompanionService {

    @Autowired
    private CompanionRepository repository;

    public void registerCompanionsToTrip(List<String> companionsToInvite, Trip trip) {
        List<Companion> companions = companionsToInvite.stream().map(email -> new Companion(email, trip)).toList() ;
        this.repository.saveAll(companions);
    }

    public void triggerConfirmationEmailToCompanions(UUID tripId) {}

    public void triggerConfirmationEmailToCompanion(String email) {}

    public CompanionCreateResponse registerCompanionToTrip(String email, Trip trip) {
        Companion companion = new Companion(email, trip);
        this.repository.save(companion);
        return new CompanionCreateResponse(companion.getId());
    }

    public List<CompanionData> getAllCompanionsFromTrip(UUID tripId) {
        return this.repository.findByTripId(tripId).stream().map(companion -> new CompanionData(companion.getId(),
                companion.getName(), companion.getEmail(), companion.getIsConfirmed())).toList();
    }
}




