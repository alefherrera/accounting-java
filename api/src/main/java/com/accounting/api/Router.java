package com.accounting.api;

import com.accounting.api.handlers.GetBalanceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> getBalance(GetBalanceHandler getBalanceHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/"), getBalanceHandler::getBalance);
    }

}
