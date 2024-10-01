package com.rmtech.next_trip_planner.place;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @autor raqueldarellimichelon
 * created on 24/09/24 inside the package - com.rmtech.next_trip_planner.place
 **/
@RestController
@RequestMapping("/places")
public class PlaceController {
    //for simplicity, call here the repository
    private final PlaceRepository repository;

    //Spring's Auto Configuration is utilizing its dependency injection (DI) framework,
    // specifically constructor injection, to supply PlaceController with the correct
    // implementation of PlaceRepository at runtime.
    private PlaceController(PlaceRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    private ResponseEntity<Place> getPlace(@PathVariable Long id) {

        //verify if id exists
        Optional<Place> optionalPlace = repository.findById(id);
        //return optionalPlace.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        if(optionalPlace.isPresent()) {
            return ResponseEntity.ok(optionalPlace.get());
        }
        return ResponseEntity.notFound().build();
    }
}


