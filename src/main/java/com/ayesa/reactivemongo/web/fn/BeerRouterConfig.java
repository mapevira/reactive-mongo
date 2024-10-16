package com.ayesa.reactivemongo.web.fn;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - rperezv
 * @version 17/10/2024 - 10:59
 * @since jdk 1.17
 */
@Configuration
@RequiredArgsConstructor
public class BeerRouterConfig {

    public static final String BEER_PATH = "/api/v3/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerHandler beanHandler;

    @Bean
    public RouterFunction<ServerResponse> beerRoutes() {
        return route()
                .GET(BEER_PATH, accept(APPLICATION_JSON), beanHandler::listBeers)
                .GET(BEER_PATH_ID, accept(APPLICATION_JSON), beanHandler::getBeerById)
                .POST(BEER_PATH, accept(APPLICATION_JSON), beanHandler::createNewBeer)
                .PUT(BEER_PATH_ID, accept(APPLICATION_JSON), beanHandler::updateBeer)
                .PATCH(BEER_PATH_ID, accept(APPLICATION_JSON), beanHandler::patchBeer)
                .DELETE(BEER_PATH_ID, accept(APPLICATION_JSON), beanHandler::deleteBeer)
                .build();
    }
}
