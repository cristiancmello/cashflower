package com.cristiancmello.cashflower.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LancamentoContabilRequestModel {
    private String valor;

    private String dataEHora;
}
