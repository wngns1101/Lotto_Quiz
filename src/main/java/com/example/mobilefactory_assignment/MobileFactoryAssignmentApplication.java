package com.example.mobilefactory_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MobileFactoryAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileFactoryAssignmentApplication.class, args);
    }

}
