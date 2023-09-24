package org.example.app.repository;

import org.example.app.db.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends JpaRepository and provides methods for performing CRUD operations on Customer entities.
 */
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
