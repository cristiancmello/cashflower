package com.cristiancmello.cashflower.domain.usecase;

import com.cristiancmello.cashflower.presentation.LancamentoContabilRequestModel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LancamentoCreditoRequest extends LancamentoContabilRequestModel {
    @Builder
    public LancamentoCreditoRequest(String valor, String dataEHora) {
        super(valor, dataEHora);
    }
}
