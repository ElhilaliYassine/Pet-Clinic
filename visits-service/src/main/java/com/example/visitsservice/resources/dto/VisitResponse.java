package com.example.visitsservice.resources.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitResponse {
    private Long id;
    private String description;
    private LocalDate date;
    private Long petId;
    private Long vetId;
}
