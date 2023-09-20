package org.example.app;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerDao {

    private  final NamedParameterJdbcTemplate template;
    private final JdbcTemplate jdbcTemplate;

    public CustomerDao (NamedParameterJdbcTemplate template, JdbcTemplate jdbcTemplate) {
        this.template = template;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping
    public Long createCustomer (Customer customer) {
        String sql = "INSERT INTO customer (fio, phone, address) VALUES (:fio, :phone, :address) RETURNING ID";
        Map<String, Object> map = new HashMap<>();
        map.put("fio", customer.getFio());
        map.put("phone", customer.getPhone());
        map.put("address", customer.getAddress());
        return template.queryForObject(sql, map, Long.class);
    }

}
