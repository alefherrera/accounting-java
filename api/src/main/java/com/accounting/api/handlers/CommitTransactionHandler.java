package com.accounting.api.handlers;

import com.accounting.api.domain.usecases.CommitTransaction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CommitTransactionHandler {

    private final CommitTransaction commitTransaction;

    public CommitTransactionHandler(CommitTransaction commitTransaction) {
        this.commitTransaction = commitTransaction;
    }

    public Mono<ServerResponse> execute(ServerRequest request) {
        Mono<CommitTransaction.CommitTransactionModel> model = request.bodyToMono(CommitTransaction.CommitTransactionModel.class);
        return model.flatMap(this::getResponse);
    }

    private Mono<ServerResponse> getResponse(CommitTransaction.CommitTransactionModel model) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(commitTransaction.apply(model)));
    }

}
