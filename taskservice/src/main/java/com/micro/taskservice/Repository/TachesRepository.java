package com.micro.taskservice.Repository;

import com.micro.taskservice.Model.Taches;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TachesRepository extends JpaRepository<Taches, Long> {
    List<Taches> findByIdProjet(Long idProjet);
}