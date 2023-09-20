package org.example.app;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final CustomerDao dao;

    public Controller(CustomerDao dao) {
        this.dao = dao;
    }

    public Long createCustomer( @RequestBody Customer customer){
        return dao.createCustomer(customer);
    }
}
