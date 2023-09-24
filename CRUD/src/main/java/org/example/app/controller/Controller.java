package org.example.app.controller;

import org.example.app.db.Customer;
import org.example.app.repository.CustomerRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class Controller {

    private final CustomerRepo CustomerRepo;

    public Controller(CustomerRepo CustomerRepo) {
        this.CustomerRepo = CustomerRepo;
    }

    @PostMapping
    public Long createCustomer(@RequestBody Customer customer) {
        customer.setCreated(LocalDateTime.now());
        Customer savedCustomer = CustomerRepo.save(customer);
        return savedCustomer.getId();
    }

    @GetMapping
    public ResponseEntity<Customer> getCustomerById(@RequestParam long id) {
        Customer customer = CustomerRepo.findById(id).orElse(null);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build(); // Возвращаем статус 404 (Not Found)
        }
    }


    @PutMapping
    public ResponseEntity<Long> editCustomer(@RequestBody Customer customer) {
        Customer existingCustomer = CustomerRepo.findById(customer.getId()).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setFio(customer.getFio());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setPhone(customer.getPhone());
            CustomerRepo.save(existingCustomer);
            return ResponseEntity.ok(customer.getId());
        }
        return ResponseEntity.notFound().build(); // Возвращаем статус 404 (Not Found)
    }


    @DeleteMapping
    public void deleteCustomer(@RequestParam long id) {
        CustomerRepo.deleteById(id);
    }
}