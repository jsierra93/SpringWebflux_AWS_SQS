package co.com.jsierra.webfluxsqs.controllers;

import co.com.jsierra.webfluxsqs.SpringCloudSQS;
import co.com.jsierra.webfluxsqs.models.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;

@Slf4j
@Component
public class Handler {

    @Autowired
    SpringCloudSQS springCloudSQS;


    public Mono<ServerResponse> send(ServerRequest serverRequest) {
        Mono<String> msg = serverRequest.body(toMono(Message.class))  //toMono es un extractor
                .flatMap(
                        val -> springCloudSQS.send(val.getMessage())
                );

        return ServerResponse.ok()
                .body(msg, String.class);
    }


}
