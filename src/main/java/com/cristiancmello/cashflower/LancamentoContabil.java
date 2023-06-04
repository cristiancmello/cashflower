package com.cristiancmello.cashflower;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class LancamentoContabil {
    private BigDecimal valor;

    private LocalDateTime dataEHora;
}
