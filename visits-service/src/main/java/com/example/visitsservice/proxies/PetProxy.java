package com.example.visitsservice.proxies;

import com.example.visitsservice.resources.dto.PetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pets", url = "localhost:9001")
public interface PetProxy {
    @GetMapping("/pets/{petId}")
    PetResponse getPetById(@PathVariable("petId") Long petId);
}
