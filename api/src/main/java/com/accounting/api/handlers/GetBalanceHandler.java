package com.accounting.api.handlers;

import com.accounting.domain.usecases.GetBalance;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class GetBalanceHandler {

    private final GetBalance getBalance;

    public GetBalanceHandler(GetBalance getBalance) {
        this.getBalance = getBalance;
    }

    public Mono<ServerResponse> getBalance(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(getBalance.get()));
    }

}
