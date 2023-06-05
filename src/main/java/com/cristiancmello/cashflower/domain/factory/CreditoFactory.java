package com.cristiancmello.cashflower.domain.factory;

import com.cristiancmello.cashflower.domain.entity.Credito;

import java.math.BigDecimal;

public interface CreditoFactory {
    Credito create(BigDecimal valor);
}
