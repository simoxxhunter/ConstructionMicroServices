package com.micro.construction.Feing;


import com.micro.construction.Dto.ProjetsDTO;
import com.micro.construction.Service.ProjetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient ("/api/Projets")
public interface ProjetFeing {


    @PostMapping
    public ResponseEntity<ProjetsDTO> createProject(@RequestBody ProjetsDTO projetsDTO) ;

    @GetMapping
    public ResponseEntity<List<ProjetsDTO>> getAllProjects() ;

    @GetMapping("/{id}")
    public ResponseEntity<ProjetsDTO> getProjectById(@PathVariable Long id) ;

    @PutMapping("/{id}")
    public ResponseEntity<ProjetsDTO> updateProject(@PathVariable Long id, @RequestBody ProjetsDTO projetsDTO) ;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) ;
}
