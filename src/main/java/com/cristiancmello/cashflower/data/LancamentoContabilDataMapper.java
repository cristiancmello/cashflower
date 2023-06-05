package com.cristiancmello.cashflower.data;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class LancamentoContabilDataMapper {
    private BigDecimal valor;

    private LocalDateTime dataEHora;
}
