package com.micro.taskservice.Service;



import com.micro.taskservice.Dto.TachesDto;
import com.micro.taskservice.Feing.ProjetFeing;
import com.micro.taskservice.Model.Taches;
import com.micro.taskservice.Repository.TachesRepository;
import com.micro.taskservice.Response.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TachesServiceImp implements TachesService {

    @Autowired
    private TachesRepository tacheRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProjetFeing projetFeing;

    @Override
    public TachesDto createTache(TachesDto tachesDto, Long idProjet) {
        try {
            ProjectResponse projetResponse = projetFeing.getProjectById(idProjet);
            if (projetResponse == null) {
                throw new IllegalArgumentException("Projet non trouvé : ID " + idProjet);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Erreur lors de la récupération du projet : " + e.getMessage());
        }

        Taches tache = new Taches();
        tache.setNom(tachesDto.getNom());
        tache.setDate_debut(tachesDto.getDate_debut());
        tache.setDate_fin(tachesDto.getDate_fin());
        tache.setDescription(tachesDto.getDescription());
        tache.setStatu(tachesDto.getStatu());
        tache.setIdProjet(idProjet);

        Taches savedTache = tacheRepository.save(tache);

        return getTachesDto(savedTache);
    }
    @Override
    public TachesDto getTachesById(Long id) {
        Optional<Taches> optionalTaches = tacheRepository.findById(id);
        if (optionalTaches.isPresent()) {
            Taches tache = optionalTaches.get();
            return getTachesDto(tache);
        }
        return null;
    }

    @Override
    public void deleteTache(Long id) {
        String url = "http://localhost:8083/api/Ressource/tache/" + id;

        try {
            restTemplate.delete(url);
        } catch (Exception e) {
            throw new IllegalStateException("error deleting the task's resoirces " + id, e);
        }

        tacheRepository.deleteById(id);
    }

    @Override
    public List<TachesDto> getAllTaches() {
        List<Taches> tachesList = tacheRepository.findAll();
        return tachesList.stream().map(tache -> {
            return getTachesDto(tache);
        }).collect(Collectors.toList());
    }

    private TachesDto getTachesDto(Taches tache) {
        TachesDto dto = new TachesDto();
        dto.setId(tache.getId());
        dto.setNom(tache.getNom());
        dto.setDate_debut(tache.getDate_debut());
        dto.setDate_fin(tache.getDate_fin());
        dto.setDescription(tache.getDescription());
        dto.setStatu(tache.getStatu());
        dto.setIdProjet(tache.getIdProjet());
        return dto;
    }

    @Override
    public TachesDto updateTache(Long id, TachesDto tachesDto) {
        Optional<Taches> optionalTaches = tacheRepository.findById(id);
        if (optionalTaches.isPresent()) {
            Taches existingTache = optionalTaches.get();
            existingTache.setNom(tachesDto.getNom());
            existingTache.setDate_debut(tachesDto.getDate_debut());
            existingTache.setDate_fin(tachesDto.getDate_fin());
            existingTache.setDescription(tachesDto.getDescription());
            existingTache.setStatu(tachesDto.getStatu());
            existingTache.setIdProjet(tachesDto.getIdProjet());

            Taches updatedTache = tacheRepository.save(existingTache);

            return getTachesDto(updatedTache);
        }
        return null;
    }

    public void deleteTachesByProjetId(Long idProjet) {
        List<Taches> taches = tacheRepository.findByIdProjet(idProjet);
        if (taches != null && !taches.isEmpty()) {
            tacheRepository.deleteAll(taches);
        }
    }



}