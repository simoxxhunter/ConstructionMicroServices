package com.micro.taskservice.Service;



import com.micro.taskservice.Dto.TachesDto;
import com.micro.taskservice.Model.Taches;
import com.micro.taskservice.Repository.TachesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TachesService implements ITachesService {

    @Autowired
    private TachesRepository tacheRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public TachesDto createTache(TachesDto tachesDto, Long idProjet) {
        try {
            restTemplate.getForObject("http://localhost:8081/api/Projets/" + idProjet, Object.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Projet non trouvé : " + e);
        }

        Taches tache = new Taches();
        tache.setNom(tachesDto.getNom());
        tache.setDate_debut(tachesDto.getDate_debut());
        tache.setDate_fin(tachesDto.getDate_fin());
        tache.setDescription(tachesDto.getDescription());
        tache.setStatu(tachesDto.getStatu());
        tache.setIdProjet(idProjet);

        Taches savedTache = tacheRepository.save(tache);

        TachesDto resultDto = new TachesDto();
        resultDto.setId(savedTache.getId());
        resultDto.setNom(savedTache.getNom());
        resultDto.setDate_debut(savedTache.getDate_debut());
        resultDto.setDate_fin(savedTache.getDate_fin());
        resultDto.setDescription(savedTache.getDescription());
        resultDto.setStatu(savedTache.getStatu());
        resultDto.setIdProjet(savedTache.getIdProjet());

        return resultDto;
    }

    @Override
    public TachesDto getTachesById(Long id) {
        Optional<Taches> optionalTaches = tacheRepository.findById(id);
        if (optionalTaches.isPresent()) {
            Taches tache = optionalTaches.get();
            TachesDto tachesDto = new TachesDto();
            tachesDto.setId(tache.getId());
            tachesDto.setNom(tache.getNom());
            tachesDto.setDate_debut(tache.getDate_debut());
            tachesDto.setDate_fin(tache.getDate_fin());
            tachesDto.setDescription(tache.getDescription());
            tachesDto.setStatu(tache.getStatu());
            tachesDto.setIdProjet(tache.getIdProjet());
            return tachesDto;
        }
        return null;
    }

    @Override
    public void deleteTache(Long id) {
        // URL du service Ressource
        String url = "http://localhost:8083/api/Ressource/tache/" + id;

        try {
            // D'abord, supprimer les ressource liées à ce tache
            restTemplate.delete(url);
        } catch (Exception e) {
            throw new IllegalStateException("Erreur lors de la suppression des ressource pour l'ID du  tache " + id, e);
        }

        // Ensuite, supprimer le atche
        tacheRepository.deleteById(id);
    }

    @Override
    public List<TachesDto> getAllTaches() {
        List<Taches> tachesList = tacheRepository.findAll();
        return tachesList.stream().map(tache -> {
            TachesDto dto = new TachesDto();
            dto.setId(tache.getId());
            dto.setNom(tache.getNom());
            dto.setDate_debut(tache.getDate_debut());
            dto.setDate_fin(tache.getDate_fin());
            dto.setDescription(tache.getDescription());
            dto.setStatu(tache.getStatu());
            dto.setIdProjet(tache.getIdProjet());
            return dto;
        }).collect(Collectors.toList());
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

            TachesDto resultDto = new TachesDto();
            resultDto.setId(updatedTache.getId());
            resultDto.setNom(updatedTache.getNom());
            resultDto.setDate_debut(updatedTache.getDate_debut());
            resultDto.setDate_fin(updatedTache.getDate_fin());
            resultDto.setDescription(updatedTache.getDescription());
            resultDto.setStatu(updatedTache.getStatu());
            resultDto.setIdProjet(updatedTache.getIdProjet());

            return resultDto;
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