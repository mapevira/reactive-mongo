package com.ayesa.reactivemongo.web.fn;

import com.ayesa.reactivemongo.model.BeerDTO;
import com.ayesa.reactivemongo.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
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
    private final Validator validator;

    private void validate(BeerDTO beerDTO) {
        Errors errors = new BeanPropertyBindingResult(beerDTO, "beerDto");
        validator.validate(beerDTO, errors);

        if (errors.hasErrors()) {
            throw new ServerWebInputException(errors.toString());
        }
    }

    public Mono<ServerResponse> listBeers(ServerRequest request) {
        Flux<BeerDTO> flux;

        if (request.queryParam("beerStyle").isPresent()) {
            flux = beerService.findByBeerStyle(request.queryParam("beerStyle").get());
        } else
            flux = beerService.listBeers();

        return ServerResponse.ok()
                .body(flux, BeerDTO.class);
    }

    public Mono<ServerResponse> getBeerById(ServerRequest request) {
        String beerId = request.pathVariable(BEER_ID);
        return ServerResponse.ok()
                .body(beerService.getBeerById(beerId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))),
                        BeerDTO.class);
    }

    public Mono<ServerResponse> createNewBeer(ServerRequest request) {
        Mono<BeerDTO> beerDTOMono = request.bodyToMono(BeerDTO.class)
                .doOnNext(this::validate);

        return beerService.createNewBeer(beerDTOMono)
                .flatMap(beerDTO -> ServerResponse
                        .created(UriComponentsBuilder
                                .fromPath("http://localhost:8080" + BeerRouterConfig.BEER_PATH_ID)
                                .build(beerDTO.getId()))
                        .build());

    }

    public Mono<ServerResponse> updateBeer(ServerRequest request) {
        String beerId = request.pathVariable(BEER_ID);
        Mono<BeerDTO> beerDTOMono = request.bodyToMono(BeerDTO.class)
                .doOnNext(this::validate);

        return beerDTOMono.flatMap(beerDTO ->
            beerService.updateBeer(beerId, beerDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .flatMap(saveDTO -> ServerResponse
                            .noContent().build())
        );

    }

    public Mono<ServerResponse> patchBeer(ServerRequest request) {
        String beerId = request.pathVariable(BEER_ID);
        Mono<BeerDTO> beerDTOMono = request.bodyToMono(BeerDTO.class)
                .doOnNext(this::validate);

        return beerDTOMono.flatMap(beerDTO ->
            beerService.patchBeer(beerId, beerDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .flatMap(saveDTO -> ServerResponse
                            .noContent().build())
        );

    }

    public Mono<ServerResponse> deleteBeer(ServerRequest request) {
        String beerId = request.pathVariable(BEER_ID);

        return beerService.getBeerById(beerId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .flatMap(beerDTO -> beerService.deleteBeerById(beerDTO.getId()))
                .then(ServerResponse.noContent().build());

    }

}
