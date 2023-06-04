package com.cristiancmello.cashflower;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "credito")
public class Credito extends LancamentoContabil {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Builder
    public Credito(BigDecimal valor, LocalDateTime dataEHora, Long id) {
        super(valor, dataEHora);
        this.id = id;
    }
}
