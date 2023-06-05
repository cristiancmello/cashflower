package com.cristiancmello.cashflower.domain.usecase;

import com.cristiancmello.cashflower.domain.entity.TipoMovimentacao;
import com.cristiancmello.cashflower.presentation.LancamentoContabilRequestModel;
import com.cristiancmello.cashflower.presentation.LancamentoContabilResponseModel;

public interface LancamentoContabilInputBoundary {
    LancamentoContabilResponseModel lanca(LancamentoContabilRequestModel request, TipoMovimentacao tipoMovimentacao);

    LancamentoContabilResponseModel lancaCredito(LancamentoCreditoRequest request);

    LancamentoContabilResponseModel lancaDebito(LancamentoDebitoRequest request);
}
