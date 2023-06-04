package com.cristiancmello.cashflower;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LancamentoContabilResponseModel {
    private String id;

    private String mensagem;

    private String dataEHoraLancamento;
}
