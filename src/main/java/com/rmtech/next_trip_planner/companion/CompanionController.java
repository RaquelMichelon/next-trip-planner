package com.rmtech.next_trip_planner.companion;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * @autor raqueldarellimichelon
 * created on 09/07/24 inside the package - com.rmtech.next_trip_planner.companion
 **/
@RestController
@RequestMapping("/companions")
public class CompanionController {

    @Autowired
    private CompanionRepository repository;

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Companion> confirmCompanion(@PathVariable UUID id, @RequestBody CompanionRequestPayload payload) {
        Optional<Companion> companion = this.repository.findById(id);
        if(companion.isPresent()) {
            Companion rawCompanion = companion.get();
            rawCompanion.setIsConfirmed(true);
            rawCompanion.setName(payload.name());

            this.repository.save(rawCompanion);

            return ResponseEntity.ok(rawCompanion);
        }

        return ResponseEntity.notFound().build();
    }

}


