package com.micro.construction.Repository;

import com.micro.construction.Model.Projets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Projets, Long> {
}