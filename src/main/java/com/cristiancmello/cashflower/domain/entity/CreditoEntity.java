package com.cristiancmello.cashflower.domain.entity;

import java.math.BigDecimal;

public record CreditoEntity(BigDecimal valor) implements Credito {
    @Override
    public BigDecimal getValor() {
        return valor;
    }
}
