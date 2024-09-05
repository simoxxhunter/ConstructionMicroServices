package com.micro.utilisateurservice.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class AuthenticationRequest {

    private String email  ;
    private String password;
}
