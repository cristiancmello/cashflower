package com.cristiancmello.cashflower;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class LancamentoContabilFormatter implements LancamentoContabilPresenter {
    @Override
    public LancamentoContabilResponseModel prepareSuccessView(LancamentoContabilResponseModel lancamentoContabil) {
        lancamentoContabil.setMensagem("Lancado com sucesso!");

        return lancamentoContabil;
    }

    @Override
    public LancamentoContabilResponseModel prepareFailView(String mensagemDeErro) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensagemDeErro);
    }
}
