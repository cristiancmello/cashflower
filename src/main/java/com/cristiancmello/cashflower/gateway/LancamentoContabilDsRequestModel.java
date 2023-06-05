package com.cristiancmello.cashflower.gateway;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LancamentoContabilDsRequestModel {
    private BigDecimal valor;

    private LocalDateTime dataEHoraDeRegistro;

    public LancamentoContabilDsRequestModel(BigDecimal valor, LocalDateTime dataEHoraDeRegistro) {
        this.valor = valor;
        this.dataEHoraDeRegistro = dataEHoraDeRegistro;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataEHoraDeRegistro() {
        return dataEHoraDeRegistro;
    }

    public void setDataEHoraDeRegistro(LocalDateTime dataEHoraDeRegistro) {
        this.dataEHoraDeRegistro = dataEHoraDeRegistro;
    }
}
