package com.rbkmoney.proxy.mocketbank.mpi.handler.mpi20;

import com.rbkmoney.proxy.mocketbank.mpi.model.Card;
import com.rbkmoney.proxy.mocketbank.mpi.model.mpi20.*;
import com.rbkmoney.proxy.mocketbank.mpi.utils.MpiAction;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static io.github.benas.randombeans.api.EnhancedRandom.random;

@Configuration
@RequiredArgsConstructor
public class SuccessCardHandler implements CardHandler {

    private final List<Card> cardList;

    @Override
    public boolean isHandle(String pan) {
        return cardList.stream()
                .anyMatch(c -> c.getPan().equals(pan)
                        && c.getAction().equals(MpiAction.THREE_D_SECURE_2_0_SUCCESS.getAction()));
    }

    @Override
    public PreparationResponse prepareHandle(PreparationRequest request) {
        return PreparationResponse.builder()
                .transactionId(random(String.class))
                .protocolVersion("2")
                .html("")
                .build();
    }

    @Override
    public AuthenticationResponse authHandle(AuthenticationRequest request) {
        return AuthenticationResponse.builder()
                .transactionId(request.getTransactionId())
                .transStatus(TransactionStatus.AUTHENTICATION_SUCCESSFUL.getCode())
                .build();
    }

    @Override
    public ResultResponse resultHandle(ResultRequest request) {
        return ResultResponse.builder()
                .transactionId(request.getTransactionId())
                .transStatus(TransactionStatus.CHALLENGE_REQUIRED.getCode())
                .build();
    }
}
