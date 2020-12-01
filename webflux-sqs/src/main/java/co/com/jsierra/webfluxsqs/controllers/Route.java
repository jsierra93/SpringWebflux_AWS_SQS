package co.com.jsierra.webfluxsqs.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Route {

    @Bean
    public RouterFunction<ServerResponse> router(Handler handler) {
        return route(
                GET("/test").and(accept(MediaType.APPLICATION_JSON)), handler::test);
    }


}