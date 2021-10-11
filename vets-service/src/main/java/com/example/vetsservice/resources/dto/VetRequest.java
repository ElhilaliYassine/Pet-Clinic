package com.example.vetsservice.resources.dto;

import com.example.vetsservice.model.Speciality;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VetRequest {
    private String firstName;
    private String lastName;
    private Speciality speciality;
}
