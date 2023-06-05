package com.cristiancmello.cashflower.gateway;

public interface RegistraLancamentoContabilDsGateway {
    LancamentoContabilDsResponseModel save(CreditoDsRequestModel requestModel);

    LancamentoContabilDsResponseModel save(DebitoDsRequestModel requestModel);
}
