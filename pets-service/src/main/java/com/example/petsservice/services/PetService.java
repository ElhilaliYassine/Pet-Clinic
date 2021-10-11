package com.example.petsservice.services;

import com.example.petsservice.exceptions.NotFoundException;
import com.example.petsservice.model.Pet;
import com.example.petsservice.repositories.OwnerRepository;
import com.example.petsservice.repositories.PetRepository;
import com.example.petsservice.resources.dto.PetRequest;
import com.example.petsservice.resources.dto.PetResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PetService {
    private PetRepository petRepository;
    private OwnerRepository ownerRepository;
    private ModelMapper modelMapper;

    public PetResponse getPetById(Long petId) {
        var pet = petRepository
                .findById(petId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Pet ID is not found");
                });
        return modelMapper.map(pet, PetResponse.class);
    }

    public List<PetResponse> getPetsByOwnerId(Long id) {
        var owner = ownerRepository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("Owner Id is not found");
                });
        var listOfPets = petRepository
                .findAllByOwner(owner);
        return modelMapper
                .map(listOfPets, new TypeToken<List<PetResponse>>() {
                }.getType());
    }

    public PetResponse createPet(PetRequest petRequest, Long ownerId) {
        if (ownerId == null)
            throw new NotFoundException("Owner ID is not provided");
        var owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> {
                    throw new NotFoundException("Owner ID is not found");
                });
        ownerRepository.findById(ownerId);
        var pet = modelMapper.map(petRequest, Pet.class);
        pet.setOwner(owner);
        petRepository.save(pet);
        return modelMapper.map(pet, PetResponse.class);
    }

    public PetResponse updatePet(Long ownerId, PetRequest PetRequest) {
        var pet = petRepository.findById(ownerId)
                .map(o -> {
                    if (PetRequest.getName() != null)
                        o.setName(PetRequest.getName());
                    if (PetRequest.getBirthDate() != null)
                        o.setBirthDate(PetRequest.getBirthDate());
                    if (PetRequest.getPetType() != null)
                        o.setPetType(PetRequest.getPetType());
                    return o;
                })
                .orElseThrow(NotFoundException::new);
        return modelMapper.map(pet, PetResponse.class);
    }

    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
}
