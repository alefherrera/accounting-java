package com.accounting.api.handlers;

import com.accounting.api.domain.account.exceptions.TransactionRefusedException;
import com.accounting.api.domain.usecases.CommitTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

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
        Optional<CommitTransaction.CommitTransactionResult> result = commitTransaction.apply(model);

        if (result.isEmpty()) {
            return ServerResponse.notFound().build();
        }

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(result));
    }

}
