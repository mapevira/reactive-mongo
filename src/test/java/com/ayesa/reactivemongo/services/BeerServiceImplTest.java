package com.ayesa.reactivemongo.services;

import com.ayesa.reactivemongo.domain.Beer;
import com.ayesa.reactivemongo.mappers.BeerMapper;
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

        savedBeer.subscribe(beerDTO -> {
            System.out.println(beerDTO);
            assertNotNull(beerDTO);
            assertNotNull(beerDTO.getId());
            assertEquals(beerDTO.getBeerName(), "Space Dust");
            assertEquals(beerDTO.getBeerStyle(), "IPA");
            assertEquals(beerDTO.getPrice(), 8.99);
            assertEquals(beerDTO.getQuantityOnHand(), 12);
            assertEquals(beerDTO.getUpc(), "1233213");
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
}