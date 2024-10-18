package com.ayesa.reactivemongo.web.fn;

import com.ayesa.reactivemongo.model.BeerDTO;
import com.ayesa.reactivemongo.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - rperezv
 * @version 17/10/2024 - 09:34
 * @since jdk 1.17
 */
@Component
@RequiredArgsConstructor
public class BeerHandler {

    public static final String BEER_ID = "beerId";
    private final BeerService beerService;

    public Mono<ServerResponse> listBeers(ServerRequest request) {
        return ServerResponse.ok()
                .body(beerService.listBeers(), BeerDTO.class);
    }

    public Mono<ServerResponse> getBeerById(ServerRequest request) {
        String beerId = request.pathVariable(BEER_ID);
        return ServerResponse.ok()
                .body(beerService.getBeerById(beerId), BeerDTO.class);
    }

    public Mono<ServerResponse> createNewBeer(ServerRequest request) {
        Mono<BeerDTO> beerDTOMono = request.bodyToMono(BeerDTO.class);
        return beerService.createNewBeer(beerDTOMono)
                .flatMap(beerDTO -> ServerResponse
                        .created(UriComponentsBuilder
                                .fromPath("http://localhost:8080" + BeerRouterConfig.BEER_PATH_ID)
                                .build(beerDTO.getId()))
                        .build());

    }

    public Mono<ServerResponse> updateBeer(ServerRequest request) {
        String beerId = request.pathVariable(BEER_ID);
        Mono<BeerDTO> beerDTOMono = request.bodyToMono(BeerDTO.class);

        return beerDTOMono.flatMap(beerDTO ->
            beerService.updateBeer(beerId, beerDTO)
                    .flatMap(saveDTO -> ServerResponse
                            .noContent().build())
        );

    }

    public Mono<ServerResponse> patchBeer(ServerRequest request) {
        String beerId = request.pathVariable(BEER_ID);
        Mono<BeerDTO> beerDTOMono = request.bodyToMono(BeerDTO.class);

        return beerDTOMono.flatMap(beerDTO ->
            beerService.patchBeer(beerId, beerDTO)
                    .flatMap(saveDTO -> ServerResponse
                            .noContent().build())
        );

    }

    public Mono<ServerResponse> deleteBeer(ServerRequest request) {
        String beerId = request.pathVariable(BEER_ID);

        return beerService.deleteBeerById(beerId)
                .then(ServerResponse.noContent().build());
    }

}
