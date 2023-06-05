package com.cristiancmello.cashflower.domain.factory;

import com.cristiancmello.cashflower.domain.entity.Debito;
import com.cristiancmello.cashflower.domain.entity.DebitoEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DebitoFactoryImpl implements DebitoFactory {
    @Override
    public Debito create(BigDecimal valor) {
        return new DebitoEntity(valor);
    }
}
