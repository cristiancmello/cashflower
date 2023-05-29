package com.cristiancmello.cashflower;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LancamentoCreditoRequest {
    private String valor;

    private String dataEHora;
}
