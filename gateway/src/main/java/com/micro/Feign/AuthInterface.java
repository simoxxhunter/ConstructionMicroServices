package com.micro.Feign;



import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "utilisateurs", url = "http://localhost:8083/api/v1/auth")
public interface AuthInterface {

    @GetMapping("/validate")
    String validateToken(@RequestParam("token") String token );
}