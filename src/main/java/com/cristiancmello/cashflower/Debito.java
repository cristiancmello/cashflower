package com.cristiancmello.cashflower;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "debito")
public class Debito extends LancamentoContabil {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Builder
    public Debito(BigDecimal valor, LocalDateTime dataEHora, Long id) {
        super(valor, dataEHora);
        this.id = id;
    }
}
