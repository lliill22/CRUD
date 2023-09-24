package org.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class of the CRUD application.
 * This class is responsible for bootstrapping the application using Spring Boot.
 */
@SpringBootApplication
public class CRUDapp {
    /**
     * The main entry point of the application.
     * It starts the Spring Boot application.
     *
     * @param args The command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(CRUDapp.class, args);
    }
}
