package com.rbkmoney.proxy.mocketbank.mpi.handler.mpi20;

import com.rbkmoney.proxy.mocketbank.mpi.model.mpi20.*;

public interface CardHandler {
    boolean isHandle(String pan);

    PreparationResponse prepareHandle(PreparationRequest request);

    AuthenticationResponse authHandle(AuthenticationRequest request);

    ResultResponse resultHandle(ResultRequest request);
}
