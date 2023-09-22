package org.example.app;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {
    private final NamedParameterJdbcTemplate template;

    public CustomerDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Long createCustomer(Customer customer) {
        String sql = "INSERT INTO customer (fio, phone, address) "
                + "VALUES (:fio, :phone, :address) RETURNING ID";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("fio", customer.getFio())
                .addValue("phone", customer.getPhone())
                .addValue("address", customer.getAddress());
        return template.queryForObject(sql, parameterSource, Long.class);
    }

    public Customer getCustomerById(long id) {
        String sql = "SELECT * FROM customer WHERE customer.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return template.queryForObject(sql, parameterSource, new CustomerRowMapper());
    }

    public void editCustomer(Customer customer) {
        String sql = "UPDATE customer SET fio = :fio, address = :address, phone = :phone WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", customer.getId())
                .addValue("fio", customer.getFio())
                .addValue("phone", customer.getPhone())
                .addValue("address", customer.getAddress());
        template.update(sql, parameterSource);
    }

    public void deleteCustomer(long id) {
        String sql = "DELETE FROM customer WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        template.update(sql, parameterSource);
    }
}