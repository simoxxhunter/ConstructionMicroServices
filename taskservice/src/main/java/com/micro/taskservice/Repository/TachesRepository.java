package com.micro.taskservice.Repository;

import com.micro.taskservice.Model.Taches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TachesRepository extends JpaRepository<Taches, Long> {
}