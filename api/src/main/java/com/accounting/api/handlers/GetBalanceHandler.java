package com.accounting.api.handlers;

import com.accounting.api.domain.account.models.Balance;
import com.accounting.api.domain.usecases.GetBalance;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class GetBalanceHandler {

    private final GetBalance getBalance;

    public GetBalanceHandler(GetBalance getBalance) {
        this.getBalance = getBalance;
    }

    public Mono<ServerResponse> execute(ServerRequest request) {
        Optional<Balance> result = getBalance.get();

        if (result.isEmpty()) {
            return ServerResponse.notFound().build();
        }

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(result));
    }

}
