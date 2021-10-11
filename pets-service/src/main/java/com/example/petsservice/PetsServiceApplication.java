package com.example.petsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PetsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetsServiceApplication.class, args);
    }

}

