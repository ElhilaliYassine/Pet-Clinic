package com.example.petsservice.resources.dto;

import com.example.petsservice.model.PetType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetResponse {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private PetType petType;
}
