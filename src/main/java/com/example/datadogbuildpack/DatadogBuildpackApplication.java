package com.example.datadogbuildpack;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@SpringBootApplication
public class DatadogBuildpackApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatadogBuildpackApplication.class, args);
    }

}

@Configuration
class DatadogBuildpackRouter {

    @Bean
    public RouterFunction<ServerResponse> routes(DatadogBuildpackHandler handler) {
        return RouterFunctions.route(GET("/hello-world/{name}"), handler::helloWorld);
    }
}

@Component
@AllArgsConstructor
@Slf4j
class DatadogBuildpackHandler {

    public Mono<ServerResponse> helloWorld(ServerRequest serverRequest) {
        return ServerResponse.ok().body(Mono.just("Hello " + serverRequest.pathVariable("name")), String.class);
    }
}
