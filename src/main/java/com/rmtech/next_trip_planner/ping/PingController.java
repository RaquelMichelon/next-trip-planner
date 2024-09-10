package com.rmtech.next_trip_planner.ping;

import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @autor raqueldarellimichelon
 * created on 10/09/24 inside the package - com.rmtech.next_trip_planner.ping
 **/
@RestController //This tells Spring that this class is a Component of type RestController and capable of handling HTTP requests
@RequestMapping("/ping") //indicates which address requests must have to access this Controller
public class PingController {

    @GetMapping("/{testId}") //marks a method as a handler method for a get request > @GetMapping("/{requestedId}")
    private ResponseEntity<Ping> getPing(@PathVariable Long testId) {
        if(testId.equals(1L)) {
            Ping ping = new Ping("pong", 1L);
            return ResponseEntity.ok(ping);
        }
        return ResponseEntity.notFound().build();
    }
}


