package com.ayesa.reactivemongo.web.fn;

import com.ayesa.reactivemongo.model.BeerDTO;
import com.ayesa.reactivemongo.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
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

    private final BeerService beerService;

    public Mono<ServerResponse> listBeers(ServerRequest request) {
        return ServerResponse.ok()
                .body(beerService.listBeers(), BeerDTO.class);
    }

}
