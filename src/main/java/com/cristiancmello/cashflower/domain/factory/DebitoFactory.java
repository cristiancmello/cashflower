package com.cristiancmello.cashflower.domain.factory;

import com.cristiancmello.cashflower.domain.entity.Debito;

import java.math.BigDecimal;

public interface DebitoFactory {
    Debito create(BigDecimal valor);
}
