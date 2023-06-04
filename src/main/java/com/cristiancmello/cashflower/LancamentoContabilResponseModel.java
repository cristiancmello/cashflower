package com.cristiancmello.cashflower;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LancamentoContabilResponseModel {
    private String mensagem;

    private String dataEHoraLancamento;
}
