package com.cristiancmello.cashflower;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegistraConsolidadoRequest {
    private String valor;

    private String tipoMovimentacao;

    @JsonProperty("data_hora_lancamento")
    private String dataHoraLancamento;

    @JsonProperty("lancamento_id")
    private String lancamentoId;
}
