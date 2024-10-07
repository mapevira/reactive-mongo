package com.ayesa.reactivemongo.repositories;

import com.ayesa.reactivemongo.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Repository interface for Customer entities.
 *
 * <p>This interface extends ReactiveMongoRepository to provide reactive CRUD operations
 * for Customer entities in a MongoDB database.
 *
 * <p>Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 07/10/2024 - 08:57
 * @since jdk 1.21
 */
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}
