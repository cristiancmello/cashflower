package com.cristiancmello.cashflower;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LancamentoDebitoResponse {
    private String id;

    private String mensagem;

    private String dataEHoraLancamento;
}
