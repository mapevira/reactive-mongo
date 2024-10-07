package com.ayesa.reactivemongo.mappers;

import com.ayesa.reactivemongo.domain.Beer;
import com.ayesa.reactivemongo.model.BeerDTO;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between Beer and BeerDTO objects.
 *
 * <p>This interface uses MapStruct to generate the implementation code for mapping
 * between Beer and BeerDTO objects.
 *
 * <p>Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 07/10/2024 - 08:30
 * @since jdk 1.21
 */
@Mapper
public interface BeerMapper {

    /**
     * Converts a Beer object to a BeerDTO object.
     *
     * @param beer - Beer object to convert.
     * @return BeerDTO object.
     */
    BeerDTO beerToBeerDTO(Beer beer);

    /**
     * Converts a BeerDTO object to a Beer object.
     *
     * @param beerDTO - BeerDTO object to convert.
     * @return Beer object.
     */
    Beer beerDTOToBeer(BeerDTO beerDTO);

}
