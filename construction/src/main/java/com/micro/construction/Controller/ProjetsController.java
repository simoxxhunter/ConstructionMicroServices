package com.micro.construction.Controller;


import com.micro.construction.Dto.ProjetsDTO;
import com.micro.construction.Service.IProjetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Projets")
public class ProjetsController {

    @Autowired
    private IProjetsService projetsService;

    // Créer un nouveau projet
    @PostMapping
    public ResponseEntity<ProjetsDTO> createProject(@RequestBody ProjetsDTO projetsDTO) {
        ProjetsDTO createdProject = projetsService.createProject(projetsDTO);
        return ResponseEntity.ok(createdProject);
    }

    // Afficher la liste des projets existants
    @GetMapping
    public ResponseEntity<List<ProjetsDTO>> getAllProjects() {
        List<ProjetsDTO> projets = projetsService.getAllProjects();
        return ResponseEntity.ok(projets);
    }

    // Récupérer un projet par ID
    @GetMapping("/{id}")
    public ResponseEntity<ProjetsDTO> getProjectById(@PathVariable Long id) {
        ProjetsDTO projet = projetsService.getProjetById(id);
        if (projet != null) {
            return ResponseEntity.ok(projet);
        }
        return ResponseEntity.notFound().build();
    }

    // Mettre à jour un projet existant
    @PutMapping("/{id}")
    public ResponseEntity<ProjetsDTO> updateProject(@PathVariable Long id, @RequestBody ProjetsDTO projetsDTO) {
        ProjetsDTO updatedProject = projetsService.updateProject(id, projetsDTO);
        if (updatedProject != null) {
            return ResponseEntity.ok(updatedProject);
        }
        return ResponseEntity.notFound().build();
    }

    // Supprimer un projet existant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projetsService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
