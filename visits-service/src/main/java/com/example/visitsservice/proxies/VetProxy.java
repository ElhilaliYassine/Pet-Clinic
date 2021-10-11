package com.example.visitsservice.proxies;

import com.example.visitsservice.resources.dto.PetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vets", url = "localhost:9002")
public interface VetProxy {
    @GetMapping("/vets/{vetId}")
    PetResponse getVetById(@PathVariable("vetId") Long vetId);
}
