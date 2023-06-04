package com.cristiancmello.cashflower;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LancamentoDebitoRequest extends LancamentoContabilRequestModel {
    @Builder
    public LancamentoDebitoRequest(String valor, String dataEHora) {
        super(valor, dataEHora);
    }
}
