package com.cristiancmello.cashflower.data;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "debito")
public class DebitoDataMapper extends LancamentoContabilDataMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Builder
    public DebitoDataMapper(BigDecimal valor, LocalDateTime dataEHora, Long id) {
        super(valor, dataEHora);
        this.id = id;
    }
}
