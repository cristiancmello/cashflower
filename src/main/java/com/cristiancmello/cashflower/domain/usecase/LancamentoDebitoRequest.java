package com.cristiancmello.cashflower.domain.usecase;

import com.cristiancmello.cashflower.presentation.LancamentoContabilRequestModel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LancamentoDebitoRequest extends LancamentoContabilRequestModel {
    @Builder
    public LancamentoDebitoRequest(String valor, String dataEHora) {
        super(valor, dataEHora);
    }
}
