package com.example.visitsservice.resources.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitRequest {
    private String description;
    @JsonFormat(pattern = "DD-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate date;
    private Long petId;
    private Long vetId;
}
