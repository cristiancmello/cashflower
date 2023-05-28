package com.cristiancmello.cashflower;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LancamentoDebitoRequest {
    private String valor;

    private String dataEHora;
}
