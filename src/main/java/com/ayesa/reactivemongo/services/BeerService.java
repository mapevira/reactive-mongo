package com.ayesa.reactivemongo.services;

import com.ayesa.reactivemongo.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for managing Beer entities.
 *
 * <p>This interface defines methods for retrieving and creating Beer entities
 * using reactive programming with Project Reactor's Mono.
 *
 * <p>Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 07/10/2024 - 09:01
 * @since jdk 1.21
 */
public interface BeerService {

    /**
     * Retrieves a BeerDTO by its ID.
     *
     * @param beerId the ID of the beer to retrieve
     * @return a Mono emitting the BeerDTO if found, or empty if not found
     */
    Mono<BeerDTO> getBeerById(String beerId);

    /**
     * Creates a new Beer entity from the given BeerDTO.
     *
     * @param beerDTO the BeerDTO containing the details of the beer to create
     * @return a Mono emitting the created BeerDTO
     */
    Mono<BeerDTO> createNewBeer(Mono<BeerDTO> beerDTO);

    /**
     * Retrieves a BeerDTO by its name.
     *
     * @param beerName the name of the beer to retrieve
     * @return a Mono emitting the BeerDTO if found, or empty if not found
     */
    Mono<BeerDTO> findFirstByBeerName(String beerName);

    /**
     * Retrieves a BeerDTO by its style.
     *
     * @param beerStyle the style of the beer to retrieve
     * @return a Flux emitting the BeerDTOs with the given style
     */
    Flux<BeerDTO> findByBeerStyle(String beerStyle);

    /**
     * Retrieves all BeerDTOs.
     *
     * @return a Flux emitting all BeerDTOs
     */
    Flux<BeerDTO> listBeers();

    /**
     * Updates a Beer entity with the given ID using the details in the given BeerDTO.
     *
     * @param beerId the ID of the beer to update
     * @param beerDTO the BeerDTO containing the details to update
     * @return a Mono emitting the updated BeerDTO
     */
    Mono<BeerDTO> updateBeer(String beerId, BeerDTO beerDTO);

    /**
     * Patch a Beer entity with the given ID.
     *
     * @param beerId the ID of the beer to delete
     * @param beerDTO the BeerDTO containing the details to patch
     * @return a Mono emitting the deleted BeerDTO
     */
    Mono<BeerDTO> patchBeer(String beerId, BeerDTO beerDTO);

    /**
     * Deletes a Beer entity with the given ID.
     *
     * @param beerId the ID of the beer to delete
     * @return a Mono emitting the deleted BeerDTO
     */
    Mono<Void> deleteBeerById(String beerId);

}
