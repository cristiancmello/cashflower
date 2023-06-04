package com.cristiancmello.cashflower;

import org.springframework.stereotype.Component;

@Component
public class LancamentoContabilFormatter implements LancamentoContabilPresenter {
    @Override
    public LancamentoContabilResponseModel prepareSuccessView(LancamentoContabilResponseModel lancamentoContabil) {
        lancamentoContabil.setMensagem("Lancado com sucesso!");

        return lancamentoContabil;
    }
}
