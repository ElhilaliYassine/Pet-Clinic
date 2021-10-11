package com.example.petsservice.repositories;

import com.example.petsservice.model.Owner;
import com.example.petsservice.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findAllByOwner(Owner owner);
}
