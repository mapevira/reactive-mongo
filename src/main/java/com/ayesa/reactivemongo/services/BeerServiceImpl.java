package com.ayesa.reactivemongo.services;

import com.ayesa.reactivemongo.mappers.BeerMapper;
import com.ayesa.reactivemongo.model.BeerDTO;
import com.ayesa.reactivemongo.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - rperezv
 * @version 07/10/2024 - 09:21
 * @since jdk 1.17
 */
@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    private final BeerMapper beerMapper;

    /**
     * Retrieves a BeerDTO by its ID.
     *
     * @param beerId the ID of the beer to retrieve
     * @return a Mono emitting the BeerDTO if found, or empty if not found
     */
    @Override
    public Mono<BeerDTO> getBeerById(String beerId) {
        return beerRepository.findById(beerId)
                .map(beerMapper::beerToBeerDTO);
    }

    /**
     * Creates a new Beer entity from the given BeerDTO.
     *
     * @param beerDTO the BeerDTO containing the details of the beer to create
     * @return a Mono emitting the created BeerDTO
     */
    @Override
    public Mono<BeerDTO> createNewBeer(Mono<BeerDTO> beerDTO) {
        return beerDTO.map(beerMapper::beerDTOToBeer)
                .flatMap(beerRepository::save)
                        .map(beerMapper::beerToBeerDTO);
    }

    /**
     * Retrieves a BeerDTO by its name.
     *
     * @param beerName the name of the beer to retrieve
     * @return a Mono emitting the BeerDTO if found, or empty if not found
     */
    @Override
    public Mono<BeerDTO> findFirstByBeerName(final String beerName) {
        return beerRepository.findFirstByBeerName(beerName)
                .map(beerMapper::beerToBeerDTO);
    }

    /**
     * Retrieves a BeerDTO by its style.
     *
     * @param beerStyle the style of the beer to retrieve
     * @return a Flux emitting the BeerDTOs with the given style
     */
    @Override
    public Flux<BeerDTO> findByBeerStyle(String beerStyle) {
        return beerRepository.findByBeerStyle(beerStyle)
                .map(beerMapper::beerToBeerDTO);
    }

    /**
     * Retrieves all BeerDTOs.
     *
     * @return a Flux emitting all BeerDTOs
     */
    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .map(beerMapper::beerToBeerDTO);
    }

    /**
     * Updates a Beer entity with the given ID using the details in the given BeerDTO.
     *
     * @param beerId the ID of the beer to update
     * @param beerDTO the BeerDTO containing the details to update
     * @return a Mono emitting the updated BeerDTO
     */
    @Override
    public Mono<BeerDTO> updateBeer(String beerId, BeerDTO beerDTO) {
        return beerRepository.findById(beerId)
                .map(beer -> {
                    beer.setBeerName(beerDTO.getBeerName());
                    beer.setBeerStyle(beerDTO.getBeerStyle());
                    beer.setPrice(beerDTO.getPrice());
                    beer.setUpc(beerDTO.getUpc());
                    beer.setQuantityOnHand(beerDTO.getQuantityOnHand());
                    return beer;
                })
                .flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDTO);
    }

    /**
     * Patch a Beer entity with the given ID.
     *
     * @param beerId  the ID of the beer to delete
     * @param beerDTO the BeerDTO containing the details to patch
     * @return a Mono emitting the deleted BeerDTO
     */
    @Override
    public Mono<BeerDTO> patchBeer(String beerId, BeerDTO beerDTO) {
        return beerRepository.findById(beerId)
                .map(beer -> {
                    if (beerDTO.getBeerName() != null) {
                        beer.setBeerName(beerDTO.getBeerName());
                    }
                    if (beerDTO.getBeerStyle() != null) {
                        beer.setBeerStyle(beerDTO.getBeerStyle());
                    }
                    if (beerDTO.getPrice() != null) {
                        beer.setPrice(beerDTO.getPrice());
                    }
                    if (beerDTO.getUpc() != null) {
                        beer.setUpc(beerDTO.getUpc());
                    }
                    if (beerDTO.getQuantityOnHand() != null) {
                        beer.setQuantityOnHand(beerDTO.getQuantityOnHand());
                    }
                    return beer;
                })
                .flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDTO);
    }

    /**
     * Deletes a Beer entity with the given ID.
     *
     * @param beerId the ID of the beer to delete
     * @return a Mono emitting the deleted BeerDTO
     */
    @Override
    public Mono<Void> deleteBeerById(String beerId) {
        return beerRepository.deleteById(beerId);
    }

}
