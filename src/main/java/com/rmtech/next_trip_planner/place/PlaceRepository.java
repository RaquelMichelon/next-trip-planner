package com.rmtech.next_trip_planner.place;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @autor raqueldarellimichelon
 * created on 24/09/24 inside the package - com.rmtech.next_trip_planner.place
 **/
public interface PlaceRepository extends CrudRepository<Place, Long> {

}
