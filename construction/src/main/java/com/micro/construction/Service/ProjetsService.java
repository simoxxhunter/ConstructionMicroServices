package com.micro.construction.Service;

import com.micro.construction.Dto.ProjetsDTO;

import java.util.List;

public interface ProjetsService {
    ProjetsDTO createProject(ProjetsDTO projetsDTO);
    ProjetsDTO getProjetById(Long id);
    void deleteProject(Long id);
    List<ProjetsDTO> getAllProjects();
    ProjetsDTO updateProject(Long id, ProjetsDTO projetsDTO);
}
