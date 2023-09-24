package org.example.app.controller;


import org.example.app.db.Customer;
import org.example.app.repository.CustomerRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

/**
 * This class defines a REST controller for managing customer data.
 */
@RestController
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final CustomerRepo CustomerRepo;

    /**
     * Constructor for Controller class.
     *
     * @param CustomerRepo The repository for managing customer data.
     */
    public Controller(CustomerRepo CustomerRepo) {
        this.CustomerRepo = CustomerRepo;
    }

    /**
     * Creates a new customer and saves them to the database.
     *
     * @param customer The customer object to be created.
     * @return The ID of the created customer.
     */
    @PostMapping
    public Long createCustomer(@RequestBody Customer customer) {
        customer.setCreated(LocalDateTime.now());
        Customer savedCustomer = CustomerRepo.save(customer);
        logger.info( "This customer was saved to db: " + savedCustomer.getId() + " " + savedCustomer.getFio() + " " +
                savedCustomer.getPhone() + " " + savedCustomer.getAddress() + " " + savedCustomer.getCreated() + "\n");
        return savedCustomer.getId();
    }
    /**
     * Retrieves a customer by their ID.
     *
     * @param id The ID of the customer.
     * @return ResponseEntity containing the customer information if found, or a 404 error if not found.
     */
    @GetMapping
    public ResponseEntity<Customer> getCustomerById(@RequestParam long id) {
        Customer customer = CustomerRepo.findById(id).orElse(null);
        if (customer != null) {
            logger.info( "This client was received from database: " + customer.getId() + " " + customer.getFio() +
                    " " + customer.getPhone() + " " + customer.getAddress() + " " + customer.getCreated() + "\n");
            return ResponseEntity.ok(customer);
        } else {
            logger.warn( "This client not found in database\n" );
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Edits an existing customer's information.
     *
     * @param customer The updated customer object.
     * @return ResponseEntity containing the ID of the modified customer if successful, or a 404 error if customer not found.
     */
    @PutMapping
    public ResponseEntity<Long> editCustomer(@RequestBody Customer customer) {
        Customer existingCustomer = CustomerRepo.findById(customer.getId()).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setFio(customer.getFio());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setPhone(customer.getPhone());
            CustomerRepo.save(existingCustomer);
            logger.info( "This buyer has been modified, here new data :" + customer.getId() + " " + customer.getFio() +
                    " " + customer.getPhone() + " " + customer.getAddress() + " " + customer.getCreated() + "\n");
            return ResponseEntity.ok(customer.getId());
        }
        logger.warn( "This client not found in database\n" );
        return ResponseEntity.notFound().build();
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id The ID of the customer to be deleted.
     */
    @DeleteMapping
    public void deleteCustomer(@RequestParam long id) {
        CustomerRepo.deleteById(id);
        logger.info("This customer was deleted : " + id);
    }
}