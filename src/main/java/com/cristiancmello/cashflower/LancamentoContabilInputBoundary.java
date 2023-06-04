package com.cristiancmello.cashflower;

public interface LancamentoContabilInputBoundary {
    LancamentoContabilResponseModel lanca(LancamentoContabilRequestModel request, TipoMovimentacao tipoMovimentacao) throws Exception;

    LancamentoContabilResponseModel lancaCredito(LancamentoCreditoRequest request) throws Exception;

    LancamentoContabilResponseModel lancaDebito(LancamentoDebitoRequest request) throws Exception;
}
