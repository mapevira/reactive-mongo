package com.ayesa.reactivemongo.repositories;

import com.ayesa.reactivemongo.domain.Beer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Repository interface for Beer entities.
 *
 * <p>This interface extends ReactiveMongoRepository to provide reactive CRUD operations
 * for Beer entities in a MongoDB database.
 *
 * <p>Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 07/10/2024 - 08:55
 * @since jdk 1.21
 */
public interface BeerRepository extends ReactiveMongoRepository<Beer, String> {

    Mono<Beer> findFirstByBeerName(String beerName);

}
