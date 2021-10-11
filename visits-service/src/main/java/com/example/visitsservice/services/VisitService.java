package com.example.visitsservice.services;

import com.example.visitsservice.exceptions.NotFoundException;
import com.example.visitsservice.model.Visit;
import com.example.visitsservice.proxies.PetProxy;
import com.example.visitsservice.proxies.VetProxy;
import com.example.visitsservice.repositories.VisitRepository;
import com.example.visitsservice.resources.dto.VisitRequest;
import com.example.visitsservice.resources.dto.VisitResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class VisitService {

    private VisitRepository visitRepository;
    private PetProxy petProxy;
    private VetProxy vetProxy;

    private ModelMapper modelMapper;

    public VisitResponse getVisitById(@PathVariable("id") Long id) {
        var visit = visitRepository
                .findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Visit ID not found");
                });
        var pet = petProxy.getPetById(visit.getPetId());
        var vet = vetProxy.getVetById(visit.getPetId());
        if (pet == null)
            throw new NotFoundException("Pet ID is not found");
        if (vet == null)
            throw new NotFoundException("Vet ID is not found");
        return modelMapper.map(visit, VisitResponse.class);
    }

    public List<VisitResponse> getVisits() {
        var listOfVisits = visitRepository
                .findAll();
        return modelMapper
                .map(listOfVisits, new TypeToken<List<VisitResponse>>() {
                }.getType());
    }

    public VisitResponse createVisit(VisitRequest visitRequest) {
        if (visitRequest.getPetId() == null)
            throw new NotFoundException("Pet ID is not found");
        if (visitRequest.getVetId() == null)
            throw new NotFoundException("Vet ID is not found");
        var retrievedVisit = modelMapper
                .typeMap(VisitRequest.class, Visit.class)
                .addMapping(VisitRequest::getVetId, Visit::setVetId)
                .addMapping(VisitRequest::getPetId, Visit::setPetId)
                .map(visitRequest);
        var visit = visitRepository.save(retrievedVisit);
        return modelMapper.map(visit, VisitResponse.class);
    }

    public VisitResponse updateVisit(Long id, VisitRequest visitRequest) {
        var visit = visitRepository.findById(id)
                .map(o -> {
                    if (visitRequest.getDate() != null)
                        o.setDate(visitRequest.getDate());
                    if (visitRequest.getDescription() != null)
                        o.setDescription(visitRequest.getDescription());
                    if (visitRequest.getPetId() != null)
                        o.setPetId(visitRequest.getPetId());
                    if (visitRequest.getVetId() != null)
                        o.setVetId(visitRequest.getVetId());
                    return o;
                })
                .orElseThrow(NotFoundException::new);
        return modelMapper.map(visit, VisitResponse.class);
    }

    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }
}
