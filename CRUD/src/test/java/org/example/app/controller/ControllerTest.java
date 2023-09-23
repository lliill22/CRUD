package org.example.app.controller;

import org.checkerframework.checker.units.qual.C;
import org.example.app.db.Customer;
import org.example.app.repository.CustomerDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

public class ControllerTest {

    private CustomerDao dao = Mockito.mock(CustomerDao.class);
    private Controller controller;
    @BeforeEach
    public void setUp() {
        controller = new Controller(dao);
        Customer customer1 = new Customer(1, "ASDzxc", "898988989", "qwertyui", LocalDateTime.now());
        Customer customerUpdated = new Customer(2, "update", "878878", "apioweopa", LocalDateTime.now());
        Customer customerDelete= new Customer(3, "ASDzxc", "898988989", "qwertyui", LocalDateTime.now());
        when(dao.getCustomerById(1)).thenReturn(customer1);
        when(dao.getCustomerById(5)).thenReturn(null);
        when(dao.createCustomer(customer1)).thenReturn(1l);
        when(dao.getCustomerById(3)).thenReturn(null);
    }

    @Test
    public void getCustomerById_testOk() {
        Assertions.assertTrue(controller.getCustomerById(1).getFio().equalsIgnoreCase("ASDzxc"));
    }
    @Test
    public void getCustomerById_testNotOk() {
        Assertions.assertEquals(controller.getCustomerById(5), null);
    }
}
