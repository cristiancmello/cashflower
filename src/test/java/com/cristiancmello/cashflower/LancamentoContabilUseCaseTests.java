package com.cristiancmello.cashflower;

import com.cristiancmello.cashflower.domain.entity.TipoMovimentacao;
import com.cristiancmello.cashflower.domain.usecase.LancamentoContabilUseCase;
import com.cristiancmello.cashflower.domain.usecase.LancamentoCreditoRequest;
import com.cristiancmello.cashflower.presentation.LancamentoContabilRequestModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class LancamentoContabilUseCaseTests {
    @Autowired
    private LancamentoContabilUseCase lancamentoContabilUseCase;

    @Test
    void deveLancarExcecaoAoNaoEnviarValorDeCredito() {
        var request = LancamentoCreditoRequest.builder()
            .build();

        assertThatThrownBy(() -> {
            lancamentoContabilUseCase.lanca(request, TipoMovimentacao.CREDITO);
        }).isInstanceOf(ResponseStatusException.class);
    }

    @Test
    void deveLancarExcecaoAoNaoEnviarValorDeDebito() {
        var request = LancamentoCreditoRequest.builder()
            .build();

        assertThatThrownBy(() -> {
            lancamentoContabilUseCase.lanca(request, TipoMovimentacao.DEBITO);
        }).isInstanceOf(ResponseStatusException.class);
    }
}
