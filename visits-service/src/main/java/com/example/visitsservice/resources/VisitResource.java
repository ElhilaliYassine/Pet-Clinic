package com.example.visitsservice.resources;

import com.example.visitsservice.resources.dto.VisitRequest;
import com.example.visitsservice.resources.dto.VisitResponse;
import com.example.visitsservice.services.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("visits")
public class VisitResource {

    private VisitService visitService;

    @GetMapping("/{id}")
    public VisitResponse getVisitById(@PathVariable("id") Long id) {
        return visitService.getVisitById(id);
    }

    @GetMapping
    public List<VisitResponse> getVisits() {
        return visitService.getVisits();
    }

    @PostMapping
    public ResponseEntity<VisitResponse> createVisit(@RequestBody VisitRequest visitRequest) {
        var Visit = visitService.createVisit(visitRequest);
        return new ResponseEntity<>(Visit, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VisitResponse> updateVisit(@PathVariable("id") Long id,
                                                 @RequestBody VisitRequest visitRequest) {
        var Visit = visitService.updateVisit(id, visitRequest);
        return new ResponseEntity<>(Visit, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVisit(@PathVariable("id") Long id) {
        visitService.deleteVisit(id);
        return ResponseEntity.noContent().build();
    }
}
