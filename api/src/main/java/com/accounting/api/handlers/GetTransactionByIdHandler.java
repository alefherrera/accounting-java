package com.accounting.api.handlers;

import com.accounting.api.domain.account.models.Transaction;
import com.accounting.api.domain.usecases.GetTransactionById;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class GetTransactionByIdHandler {

    private final GetTransactionById getTransactionById;

    public GetTransactionByIdHandler(GetTransactionById getTransactionById) {
        this.getTransactionById = getTransactionById;
    }

    public Mono<ServerResponse> execute(ServerRequest request) {
        String id = request.pathVariable("id");
        Optional<Transaction> result = getTransactionById.apply(id);

        if (result.isEmpty()) {
            return ServerResponse.notFound().build();
        }

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(result));
    }

}
