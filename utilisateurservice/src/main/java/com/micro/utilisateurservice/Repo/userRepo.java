package com.micro.utilisateurservice.Repo;

import com.micro.utilisateurservice.model.userModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepo extends JpaRepository<userModel, Long> {
    Optional<userModel> findByEmail(String email);

}
