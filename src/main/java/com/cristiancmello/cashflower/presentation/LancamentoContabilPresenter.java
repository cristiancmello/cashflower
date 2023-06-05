package com.cristiancmello.cashflower.presentation;

public interface LancamentoContabilPresenter {
    LancamentoContabilResponseModel prepareSuccessView(LancamentoContabilResponseModel lancamentoContabil);

    LancamentoContabilResponseModel prepareFailView(String mensagemDeErro);
}
