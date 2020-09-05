package com.accounting.api.handlers;

import com.accounting.api.domain.usecases.GetTransactions;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class GetTransactionsHandler {

    private final GetTransactions getTransactions;

    public GetTransactionsHandler(GetTransactions getTransactions) {
        this.getTransactions = getTransactions;
    }

    public Mono<ServerResponse> execute(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(getTransactions.get()));
    }
}
