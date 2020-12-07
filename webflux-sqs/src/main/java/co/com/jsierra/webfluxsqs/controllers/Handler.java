package co.com.jsierra.webfluxsqs.controllers;

import co.com.jsierra.webfluxsqs.SpringCloudSQS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Component
public class Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

    @Autowired
    SpringCloudSQS springCloudSQS;

    public Mono<ServerResponse> test(ServerRequest serverRequest) {
        Scheduler sch = Schedulers.newParallel("test");
        return ServerResponse
                .ok()
                .body(Mono.just("ok")
                        .publishOn(sch)
                        .doOnNext(x ->LOGGER.info("onNext " + Thread.currentThread().getName()))
                        .subscribeOn(Schedulers.boundedElastic())
                        , String.class);
    }


    public Mono<ServerResponse> send(ServerRequest serverRequest) {
        Object message = serverRequest.headers().header("message");
     //   queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build());
      //  LOGGER.info("recibido "+message);
        springCloudSQS.send(message);
        return ServerResponse.ok()
                .body(Mono.just(message.toString()
                ), String.class);
    }


}
