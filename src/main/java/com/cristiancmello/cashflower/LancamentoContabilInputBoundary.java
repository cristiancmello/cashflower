package com.cristiancmello.cashflower;

public interface LancamentoContabilInputBoundary {
    LancamentoContabilResponseModel lanca(LancamentoContabilRequestModel requestModel);

    LancamentoContabilResponseModel lancaCredito(LancamentoCreditoRequest request);

    LancamentoContabilResponseModel lancaDebito(LancamentoDebitoRequest request);
}
