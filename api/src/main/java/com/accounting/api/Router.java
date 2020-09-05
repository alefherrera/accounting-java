package com.accounting.api;

import com.accounting.api.domain.usecases.GetTransactions;
import com.accounting.api.handlers.CommitTransactionHandler;
import com.accounting.api.handlers.GetBalanceHandler;
import com.accounting.api.handlers.GetTransactionByIdHandler;
import com.accounting.api.handlers.GetTransactionsHandler;
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
        return RouterFunctions.route(RequestPredicates.GET("/"), getBalanceHandler::execute);
    }

    @Bean
    public RouterFunction<ServerResponse> commitTransaction(CommitTransactionHandler commitTransactionHandler) {
        return RouterFunctions.route(RequestPredicates.POST("/transactions"), commitTransactionHandler::execute);
    }

    @Bean
    public RouterFunction<ServerResponse> getTransactions(GetTransactionsHandler getTransactionsHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/transactions"), getTransactionsHandler::execute);
    }

    @Bean
    public RouterFunction<ServerResponse> getTransactionById(GetTransactionByIdHandler getTransactionByIdHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/transactions/{id}"), getTransactionByIdHandler::execute);
    }

}
