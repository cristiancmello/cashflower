package com.cristiancmello.cashflower.domain.entity;

import java.math.BigDecimal;

public record DebitoEntity(BigDecimal valor) implements Debito {
    @Override
    public BigDecimal getValor() {
        return valor;
    }
}
