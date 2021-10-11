package com.example.vetsservice.resources;

import com.example.vetsservice.services.VetService;
import com.example.vetsservice.resources.dto.VetRequest;
import com.example.vetsservice.resources.dto.VetResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("vets")
public class VetResource {

    private VetService vetService;

    @GetMapping("/{id}")
    public VetResponse getVetById(@PathVariable("id") Long id) {
        return vetService.getVetById(id);
    }

    @GetMapping
    public List<VetResponse> getVets() {
        return vetService.getVets();
    }

    @PostMapping
    public ResponseEntity<VetResponse> createVet(@RequestBody VetRequest VetRequest) {
        var Vet = vetService.createVet(VetRequest);
        return new ResponseEntity<>(Vet, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VetResponse> updateVet(@PathVariable("id") Long id,
                                                 @RequestBody VetRequest vetRequest) {
        var Vet = vetService.updateVet(id, vetRequest);
        return new ResponseEntity<>(Vet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVet(@PathVariable("id") Long id) {
        vetService.deleteVet(id);
        return ResponseEntity.noContent().build();
    }
}
