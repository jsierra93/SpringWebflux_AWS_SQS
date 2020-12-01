package co.com.jsierra.webfluxsqs.controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class Handler {

     public Mono<ServerResponse> test(ServerRequest serverRequest) {
             return ServerResponse
                     .ok()
                     .body(response(), String.class);
         }

         public Mono<String> response(){
         return Mono.just("prueba");
         }
}
