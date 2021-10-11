package com.example.petsservice.resources.dto;

import com.example.petsservice.model.PetType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetRequest {
    private String name;
    @JsonFormat(pattern = "DD-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate birthDate;
    private PetType petType;
}
