package com.micro.taskservice.Feing;



import com.micro.taskservice.Response.ProjectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "projet-service", url = "http://localhost:8081/api/Projets")
public interface ProjetFeing {

    @GetMapping("/{id}")
    ProjectResponse getProjectById(@PathVariable("id") Long id);

}
