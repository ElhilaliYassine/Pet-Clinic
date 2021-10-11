package com.example.petsservice.resources;

import com.example.petsservice.resources.dto.PetRequest;
import com.example.petsservice.resources.dto.PetResponse;
import com.example.petsservice.services.PetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("pets")
public class PetResource {

    private PetService petService;

    @GetMapping("/{petId}")
    public PetResponse getPetById(@PathVariable("petId") Long petId) {
        return petService.getPetById(petId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> updatePet(@PathVariable("id") Long id,
                                                 @RequestBody PetRequest petRequest) {
        var pet = petService.updatePet(id, petRequest);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable("id") Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
