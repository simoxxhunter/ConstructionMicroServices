package com.micro.construction.Service;


import com.micro.construction.Dto.ProjetsDTO;

import com.micro.construction.Model.Projets;
import com.micro.construction.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjetsServiceImp implements ProjetsService {

    @Autowired
    private ProjectRepository projetsRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public ProjetsDTO createProject(ProjetsDTO projetsDTO) {
        System.out.println("Creating project with name: " + projetsDTO.getNom());

        Projets projet = new Projets();
        projet.setId(projetsDTO.getId());
        projet.setNom(projetsDTO.getNom());
        projet.setDescription(projetsDTO.getDescription());
        projet.setDate_debut(projetsDTO.getDate_debut());
        projet.setDate_fin(projetsDTO.getDate_fin());
        projet.setBudget(projetsDTO.getBudget());

        System.out.println("Mapped project entity: " + projet);

        projet = projetsRepository.save(projet);

        ProjetsDTO savedProjetsDTO = new ProjetsDTO();
        savedProjetsDTO.setId(projet.getId());
        savedProjetsDTO.setNom(projet.getNom());
        savedProjetsDTO.setDescription(projet.getDescription());
        savedProjetsDTO.setDate_debut(projet.getDate_debut());
        savedProjetsDTO.setDate_fin(projet.getDate_fin());
        savedProjetsDTO.setBudget(projet.getBudget());

        return savedProjetsDTO;
    }

    @Override
    public List<ProjetsDTO> getAllProjects() {
        List<Projets> projets = projetsRepository.findAll();
        return projets.stream().map(projet -> {
            ProjetsDTO dto = new ProjetsDTO();
            dto.setId(projet.getId());
            dto.setNom(projet.getNom());
            dto.setDescription(projet.getDescription());
            dto.setDate_debut(projet.getDate_debut());
            dto.setDate_fin(projet.getDate_fin());
            dto.setBudget(projet.getBudget());
            return dto;
        }).collect(Collectors.toList());

    }

    @Override
    public ProjetsDTO updateProject(Long id, ProjetsDTO projetsDTO) {
        Projets existingProjet = projetsRepository.findById(id).orElse(null);
        if (existingProjet != null) {
            existingProjet.setNom(projetsDTO.getNom());
            existingProjet.setDescription(projetsDTO.getDescription());
            existingProjet.setDate_debut(projetsDTO.getDate_debut());
            existingProjet.setDate_fin(projetsDTO.getDate_fin());
            existingProjet.setBudget(projetsDTO.getBudget());

            projetsRepository.save(existingProjet);

            ProjetsDTO updatedProjetsDTO = new ProjetsDTO();
            updatedProjetsDTO.setId(existingProjet.getId());
            updatedProjetsDTO.setNom(existingProjet.getNom());
            updatedProjetsDTO.setDescription(existingProjet.getDescription());
            updatedProjetsDTO.setDate_debut(existingProjet.getDate_debut());
            updatedProjetsDTO.setDate_fin(existingProjet.getDate_fin());
            updatedProjetsDTO.setBudget(existingProjet.getBudget());

            return updatedProjetsDTO;
        }
        return null;
    }


    @Override
    public void deleteProject(Long id) {
        String url = "http://localhost:8082/api/Taches/projet/" + id;

        try {
            restTemplate.delete(url);
        } catch (Exception e) {
            throw new IllegalStateException("error deleting the task's resoirces : " + id, e);
        }

        projetsRepository.deleteById(id);
    }

    @Override
    public ProjetsDTO getProjetById(Long id) {
        Optional<Projets> optionalProjet = projetsRepository.findById(id);
        if (optionalProjet.isPresent()) {
            Projets projet = optionalProjet.get();
            ProjetsDTO dto = new ProjetsDTO();
            dto.setId(projet.getId());
            dto.setNom(projet.getNom());
            dto.setDescription(projet.getDescription());
            dto.setDate_debut(projet.getDate_debut());
            dto.setDate_fin(projet.getDate_fin());
            dto.setBudget(projet.getBudget());
            return dto;
        }
        return null;
    }
}