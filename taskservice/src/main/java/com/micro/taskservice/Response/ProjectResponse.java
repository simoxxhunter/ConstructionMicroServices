package com.micro.taskservice.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {

    private Long id;
    private String nom;
    private String description;
    private LocalDate date_debut;
    private LocalDate date_fin;
    private Double budget;
}
