package com.ayesa.reactivemongo.services;

import com.ayesa.reactivemongo.domain.Beer;
import com.ayesa.reactivemongo.mappers.BeerMapper;
import com.ayesa.reactivemongo.mappers.BeerMapperImpl;
import com.ayesa.reactivemongo.model.BeerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerServiceImplTest {

    @Autowired
    BeerService beerService;

    @Autowired
    BeerMapper beerMapper;

    BeerDTO beerDTO;

    @BeforeEach
    void setUp() {
        beerDTO = beerMapper.beerToBeerDTO(BeerServiceImplTest.getTestBeer());
    }

    @Test
    void testCreateNewBeer() {
        AtomicBoolean created = new AtomicBoolean();

        Mono<BeerDTO> savedBeer = beerService.createNewBeer(Mono.just(beerDTO));

        savedBeer.subscribe(dto -> {
            System.out.println(dto);
            assertNotNull(dto);
            assertNotNull(dto.getId());
            assertEquals("Space Dust", dto.getBeerName());
            assertEquals("IPA", dto.getBeerStyle());
            assertEquals(8.99, dto.getPrice());
            assertEquals(12, dto.getQuantityOnHand() );
            assertEquals("1233213", dto.getUpc());
            created.set(true);
        });

        await().untilTrue(created);
    }

    public static Beer getTestBeer() {
        return Beer.builder()
                .beerName("Space Dust")
                .beerStyle("IPA")
                .price(8.99)
                .quantityOnHand(12)
                .upc("1233213")
                .build();
    }

    public BeerDTO getSavedBeerDto(){
        return beerService.createNewBeer(Mono.just(getTestBeerDto())).block();
    }

    public static BeerDTO getTestBeerDto(){
        return new BeerMapperImpl().beerToBeerDTO(getTestBeer());
    }

    @Test
    void findFirstByBeerNameTest() {
        BeerDTO beerDto = getSavedBeerDto();

        Mono<BeerDTO> beerMono = beerService.findFirstByBeerName(beerDto.getBeerName());

        beerMono.subscribe(dto -> {
            assertNotNull(dto);
            assertEquals(beerDto.getId(), dto.getId());
            assertEquals(beerDto.getBeerName(), dto.getBeerName());
            assertEquals(beerDto.getBeerStyle(), dto.getBeerStyle());
            assertEquals(beerDto.getPrice(), dto.getPrice());
            assertEquals(beerDto.getQuantityOnHand(), dto.getQuantityOnHand());
            assertEquals(beerDto.getUpc(), dto.getUpc());
        });
    }

}