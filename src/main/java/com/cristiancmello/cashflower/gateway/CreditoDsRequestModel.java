package com.cristiancmello.cashflower.gateway;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreditoDsRequestModel extends LancamentoContabilDsRequestModel {
    public CreditoDsRequestModel(BigDecimal valor, LocalDateTime dataEHoraDeRegistro) {
        super(valor, dataEHoraDeRegistro);
    }
}
