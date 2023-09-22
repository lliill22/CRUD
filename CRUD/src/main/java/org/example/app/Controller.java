package org.example.app;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private final CustomerDao dao;

    public Controller(CustomerDao dao) {
        this.dao = dao;
    }

    @PostMapping
    public Long createCustomer(@RequestBody Customer customer) {
        return dao.createCustomer(customer);
    }

    @GetMapping
    public Customer getCustomerById(@RequestParam long id) {
        return dao.getCustomerById(id);
    }

    @PutMapping
    public void editCustomer(@RequestBody Customer customer) {
        dao.editCustomer(customer);
    }

    @DeleteMapping
    public void deleteCustomer(@RequestParam long id) {
        dao.deleteCustomer(id);
    }
}