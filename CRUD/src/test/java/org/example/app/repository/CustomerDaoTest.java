package org.example.app.repository;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.app.db.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


public class CustomerDaoTest {
    EmbeddedDatabase database;
    private CustomerDao dao;
    private final Customer customerGet = new  Customer(1, "Bogdan", "898989898", "school21", LocalDateTime.parse("2023 Sep 23 14:19:48", DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm:ss")));
    private final Customer customerEdit = new Customer(5, "Vova", "887873", "school21", LocalDateTime.parse("2023 Sep 23 14:01:12", DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm:ss")));
    private final Customer customerDelete = new Customer(6, "Daria", "887889898","school21", LocalDateTime.parse("2023 Sep 23 14:02:14", DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm:ss")));
    @BeforeEach
    void init ( ) {
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("init.sql")
                .build();
         dao = new CustomerDao(new NamedParameterJdbcTemplate(database));
    }
    @Test
    public void createCustomerTest(){

    }
    @Test
    public void getCustomerByIdTest() {
        Customer res = dao.getCustomerById(1);
        Assertions.assertEquals(customerGet, res);
    }
    @Test
    public void editCustomerTest(){
        dao.editCustomer(new Customer(5,"Lesha", "887873", "school21", LocalDateTime.parse("2023 Sep 23 14:01:12", DateTimeFormatter.ofPattern("yyyy MMM dd HH:mm:ss"))));
        Assertions.assertNotEquals(customerEdit, dao.getCustomerById(5));
    }
    @Test
    public void deleteCustomerById(){

    }
}
