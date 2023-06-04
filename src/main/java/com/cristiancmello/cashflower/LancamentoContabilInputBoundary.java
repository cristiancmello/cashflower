package com.cristiancmello.cashflower;

public interface LancamentoContabilInputBoundary {
    LancamentoContabilResponseModel lanca(LancamentoContabilRequestModel request, TipoMovimentacao tipoMovimentacao);

    LancamentoContabilResponseModel lancaCredito(LancamentoCreditoRequest request);

    LancamentoContabilResponseModel lancaDebito(LancamentoDebitoRequest request);
}
