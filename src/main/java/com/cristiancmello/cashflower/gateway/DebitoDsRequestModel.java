package com.cristiancmello.cashflower.gateway;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DebitoDsRequestModel extends LancamentoContabilDsRequestModel {
    public DebitoDsRequestModel(BigDecimal valor, LocalDateTime dataEHoraDeRegistro) {
        super(valor, dataEHoraDeRegistro);
    }
}
