package com.cristiancmello.cashflower.domain.factory;

import com.cristiancmello.cashflower.domain.entity.Credito;
import com.cristiancmello.cashflower.domain.entity.CreditoEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreditoFactoryImpl implements CreditoFactory {
    @Override
    public Credito create(BigDecimal valor) {
        return new CreditoEntity(valor);
    }
}
