package com.rmtech.next_trip_planner.trip;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @autor raqueldarellimichelon
 * created on 09/07/24 inside the package - com.rmtech.next_trip_planner.trip
 **/
@Entity
@Table(name="trips")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String destination;

    @Column(name="starts_at", nullable = false)
    private LocalDateTime startsAt;

    @Column(name="ends_at", nullable = false)
    private LocalDateTime endsAt;

    @Column(name="is_confirmed", nullable = false)
    private Boolean isConfirmed;

    @Column(name="owner_name", nullable = false)
    private String ownerName;

    @Column(name="owner_email", nullable = false)
    private String ownerEmail;


    public Trip(TripRequestPayload data) {
        this.destination = data.destination();
        this.ownerName = data.ownerName();
        this.ownerEmail = data.ownerEmail();
        this.isConfirmed = false;
        this.startsAt = LocalDateTime.parse(data.startsAt(), DateTimeFormatter.ISO_DATE_TIME);
        this.endsAt = LocalDateTime.parse(data.endsAt(), DateTimeFormatter.ISO_DATE_TIME);

    }
}


