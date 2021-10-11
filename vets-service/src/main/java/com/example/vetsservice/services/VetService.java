package com.example.vetsservice.services;

import com.example.vetsservice.exceptions.NotFoundException;
import com.example.vetsservice.model.Vet;
import com.example.vetsservice.repositories.VetRepository;
import com.example.vetsservice.resources.dto.VetRequest;
import com.example.vetsservice.resources.dto.VetResponse;
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
public class VetService {
    private VetRepository VetRepository;
    private ModelMapper modelMapper;

    public VetResponse getVetById(@PathVariable("id") Long id) {
        var Vet = VetRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);
        return modelMapper.map(Vet, VetResponse.class);
    }

    public List<VetResponse> getVets() {
        var listOfVets = VetRepository
                .findAll();
        return modelMapper.map(listOfVets, new TypeToken<List<VetResponse>>() {
        }.getType());
    }

    public VetResponse createVet(VetRequest VetRequest) {
        var retrievedVet = modelMapper.map(VetRequest, Vet.class);
        var vet = VetRepository.save(retrievedVet);
        return modelMapper.map(vet, VetResponse.class);
    }

    public VetResponse updateVet(Long id, VetRequest vetRequest) {
        var vet = VetRepository.findById(id)
                .map(o -> {
                    if (vetRequest.getFirstName() != null)
                        o.setFirstName(vetRequest.getFirstName());
                    if (vetRequest.getLastName() != null)
                        o.setLastName(vetRequest.getLastName());
                    if (vetRequest.getSpeciality() != null)
                        o.setSpeciality(vetRequest.getSpeciality());
                    return o;
                })
                .orElseThrow(NotFoundException::new);
        return modelMapper.map(vet, VetResponse.class);
    }

    public void deleteVet(Long id) {
        VetRepository.deleteById(id);
    }
}
