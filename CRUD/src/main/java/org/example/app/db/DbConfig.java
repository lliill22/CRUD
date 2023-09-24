package org.example.app.db;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * This class provides configuration for the database and sets up a NamedParameterJdbcTemplate bean.
 */
@Configuration
public class DbConfig {
    /**
     * Creates and configures a NamedParameterJdbcTemplate bean for database operations.
     *
     * @param dataSource The DataSource for connecting to the database.
     * @return A NamedParameterJdbcTemplate instance for executing SQL queries with named parameters.
     */
    @Bean
    public NamedParameterJdbcTemplate template(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}