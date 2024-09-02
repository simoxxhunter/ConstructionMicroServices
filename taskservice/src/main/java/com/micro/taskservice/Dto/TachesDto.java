package com.micro.taskservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TachesDto {

    private Long id;
    private String nom;
    private String date_debut;
    private String date_fin;
    private String description;
    private String statu;
    private  Long idProjet;
}