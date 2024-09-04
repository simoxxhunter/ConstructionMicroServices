package com.micro.construction.Controller;


import com.micro.construction.Dto.ProjetsDTO;
import com.micro.construction.Service.ProjetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Projets")
public class ProjetsController {

    @Autowired
    private ProjetsService projetsService;

    @PostMapping
    public ResponseEntity<ProjetsDTO> createProject(@RequestBody ProjetsDTO projetsDTO) {
        ProjetsDTO createdProject = projetsService.createProject(projetsDTO);
        return ResponseEntity.ok(createdProject);
    }

    @GetMapping
    public ResponseEntity<List<ProjetsDTO>> getAllProjects() {
        List<ProjetsDTO> projets = projetsService.getAllProjects();
        return ResponseEntity.ok(projets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetsDTO> getProjectById(@PathVariable Long id) {
        ProjetsDTO projet = projetsService.getProjetById(id);
        if (projet != null) {
            return ResponseEntity.ok(projet);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetsDTO> updateProject(@PathVariable Long id, @RequestBody ProjetsDTO projetsDTO) {
        ProjetsDTO updatedProject = projetsService.updateProject(id, projetsDTO);
        if (updatedProject != null) {
            return ResponseEntity.ok(updatedProject);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projetsService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
