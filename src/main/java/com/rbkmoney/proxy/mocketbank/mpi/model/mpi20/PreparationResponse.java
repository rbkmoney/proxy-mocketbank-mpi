package com.rbkmoney.proxy.mocketbank.mpi.model.mpi20;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.*;

@Data
@Builder
public class PreparationResponse {

    private String transactionId;

    @JsonUnwrapped
    private Error error;

    private String protocolVersion;

    private String html;
}
