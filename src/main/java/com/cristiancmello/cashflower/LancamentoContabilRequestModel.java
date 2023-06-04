package com.cristiancmello.cashflower;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LancamentoContabilRequestModel {
    private String valor;

    private String dataEHora;
}
