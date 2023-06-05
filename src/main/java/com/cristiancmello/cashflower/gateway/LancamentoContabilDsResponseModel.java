package com.cristiancmello.cashflower.gateway;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LancamentoContabilDsResponseModel {
    private Long id;

    private BigDecimal valor;

    private LocalDateTime dataEHora;

    public LancamentoContabilDsResponseModel(Long id, BigDecimal valor, LocalDateTime dataEHora) {
        this.id = id;
        this.valor = valor;
        this.dataEHora = dataEHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataEHora() {
        return dataEHora;
    }

    public void setDataEHora(LocalDateTime dataEHora) {
        this.dataEHora = dataEHora;
    }
}
