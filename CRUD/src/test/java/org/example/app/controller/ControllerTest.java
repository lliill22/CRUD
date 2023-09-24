package org.example.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.app.db.Customer;
import org.example.app.repository.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ControllerTest {
    private final CustomerRepo customerRepo = Mockito.mock(CustomerRepo.class);
    private final Controller controller = new Controller(customerRepo);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createCustomerTest() throws Exception {
        Customer customer = new Customer();
        customer.setFio("John Doe");
        customer.setPhone("123456789");
        customer.setAddress("Some Address");

        when(customerRepo.save(any(Customer.class)))
                .thenAnswer(invocation -> {
                    Customer customer1 = invocation.getArgument(0);
                    customer1.setId(1L); // Устанавливаем ID вручную
                    return customer1;
                });

        MvcResult result = mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fio\": \"John Doe\", \"phone\": \"123456789\", \"address\": \"Some Address\"}"))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertEquals("1", responseContent);

        verify(customerRepo).save(any(Customer.class));
    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        long id = 1;
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFio("John Doe");
        customer.setPhone("123456789");
        customer.setAddress("Some Address");
        customer.setCreated(LocalDateTime.now());

        when(customerRepo.findById(id)).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/?id=" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.fio", is("John Doe")))
                .andExpect(jsonPath("$.phone", is("123456789")))
                .andExpect(jsonPath("$.address", is("Some Address")))
                .andExpect(jsonPath("$.created").isNotEmpty());

        verify(customerRepo).findById(id);
    }
    @Test
    public void getNonExistentCustomerTest() throws Exception {
        long id = 2;

        when(customerRepo.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/?id=" + id))
                .andExpect(status().isNotFound()); // Ожидаем статус 404 (Not Found)
    }

    @Test
    public void editCustomerTest() throws Exception {
        long id = 1;
        Customer existingCustomer = new Customer();
        existingCustomer.setId(id);
        existingCustomer.setFio("John Doe");
        existingCustomer.setPhone("123456789");
        existingCustomer.setAddress("Some Address");

        when(customerRepo.findById(id)).thenReturn(Optional.of(existingCustomer));
        when(customerRepo.save(any(Customer.class))).thenReturn(existingCustomer);

        mockMvc.perform(put("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"fio\": \"Updated Name\", \"phone\": \"Updated Phone\", \"address\": \"Updated Address\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(is("1"))); // Ваш ожидаемый ID

        verify(customerRepo).findById(id);
        verify(customerRepo).save(any(Customer.class));
    }
    @Test
    public void editNonExistentCustomerTest() throws Exception {
        Customer nonExistentCustomer = new Customer();
        nonExistentCustomer.setId(100L); // Предполагаем, что клиента с ID 100 нет

        // Ожидаем, что запрос вернет статус 404 (Not Found)
        mockMvc.perform(put("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nonExistentCustomer)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteCustomerTest() throws Exception {
        long id = 1;

        mockMvc.perform(delete("/?id=" + id))
                .andExpect(status().isOk());

        verify(customerRepo).deleteById(id);
    }
}