package com.cristiancmello.cashflower;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LancamentoCreditoResponse {
    private String id;

    private String mensagem;

    private String dataEHoraLancamento;
}
