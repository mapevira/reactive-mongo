package com.ayesa.reactivemongo.services;

import com.ayesa.reactivemongo.domain.Beer;
import com.ayesa.reactivemongo.mappers.BeerMapper;
import com.ayesa.reactivemongo.model.BeerDTO;
import com.ayesa.reactivemongo.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

}
