package com.rmtech.next_trip_planner.place;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;

/**
 * @autor raqueldarellimichelon
 * created on 24/09/24 inside the package - com.rmtech.next_trip_planner.place
 **/
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Place {
    @Id
    private Long id;
    private String name;
}
