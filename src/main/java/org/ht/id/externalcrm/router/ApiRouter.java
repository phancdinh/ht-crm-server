package org.ht.id.externalcrm.router;

import org.ht.id.externalcrm.handler.ApiHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ApiRouter {
    @Bean
    public RouterFunction<ServerResponse> composeRoutes(ApiHandler handler) {

        return RouterFunctions
                .route(GET("/api/profiles").and(accept(MediaType.APPLICATION_JSON)), handler::getAll)
                .andRoute(POST("/api/profiles").and(accept(MediaType.APPLICATION_JSON)), handler::create);
    }
}
