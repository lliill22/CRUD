package org.example.app.db;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * This class represents a Customer entity in the database.
 */
@Entity
@Data
public class Customer {

    /**
     * The unique identifier for the customer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The full name of the customer.
     */
    private String fio;

    /**
     * The phone number of the customer.
     */
    private String phone;

    /**
     * The address of the customer.
     */
    private String address;

    /**
     * The date and time when the customer was created.
     */
    private LocalDateTime created;
}