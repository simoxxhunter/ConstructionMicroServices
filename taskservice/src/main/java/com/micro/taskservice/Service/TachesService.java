package com.micro.taskservice.Service;

import com.micro.taskservice.Dto.TachesDto;

import java.util.List;

public interface TachesService {
    TachesDto createTache(TachesDto tachesDto, Long idProjet);
    TachesDto getTachesById(Long id);
    void deleteTache(Long id);
    List<TachesDto> getAllTaches();
    TachesDto updateTache(Long id, TachesDto tachesDto);
    void deleteTachesByProjetId(Long idProjet);
}
