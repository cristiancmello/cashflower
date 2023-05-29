package com.cristiancmello.cashflower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "lancamento")
public class LancamentoController {
    @Autowired
    ConsolidadoFeignClient consolidadoFeignClient;

    @Autowired
    private LancamentoCreditoRepository lancamentoCreditoRepository;

    @Autowired
    private LancamentoDebitoRepository lancamentoDebitoRepository;

    @PostMapping("debito")
    LancamentoDebitoResponse lancaDebito(@RequestBody LancamentoDebitoRequest request) {
        // TODO: colocar uma transaction (se registraConsolidadoDiario, nao lance nada
        var lancamento = lancamentoDebitoRepository.save(Debito.builder()
            .dataEHora(LocalDateTime.now())
            .valor(BigDecimal.valueOf(200.0))
            .build());

        var registroConsolidadoRequest = RegistraConsolidadoRequest.builder()
            .valor(lancamento.getValor().toString())
            .tipoMovimentacao("DEBITO")
            .dataHoraLancamento(lancamento.getDataEHora().toString())
            .lancamentoId(lancamento.getId().toString())
            .build();

        consolidadoFeignClient.registraConsolidadoDiario(registroConsolidadoRequest);

        return LancamentoDebitoResponse.builder()
            .id(lancamento.getId().toString())
            .mensagem("Lancado com sucesso!")
            .dataEHoraLancamento(String.valueOf(LocalDateTime.now()))
            .build();
    }

    @PostMapping("credito")
    LancamentoCreditoResponse lancaCredito(@RequestBody LancamentoCreditoRequest request) {
        // TODO: colocar uma transaction (se registraConsolidadoDiario, nao lance nada
        var lancamento = lancamentoCreditoRepository.save(Credito.builder()
            .dataEHora(LocalDateTime.now())
            .valor(BigDecimal.valueOf(200.0))
            .build());

        var registroConsolidadoRequest = RegistraConsolidadoRequest.builder()
            .valor(lancamento.getValor().toString())
            .tipoMovimentacao("CREDITO")
            .dataHoraLancamento(lancamento.getDataEHora().toString())
            .lancamentoId(lancamento.getId().toString())
            .build();

        consolidadoFeignClient.registraConsolidadoDiario(registroConsolidadoRequest);

        return LancamentoCreditoResponse.builder()
            .id(lancamento.getId().toString())
            .mensagem("Lancado com sucesso!")
            .dataEHoraLancamento(String.valueOf(LocalDateTime.now()))
            .build();
    }

    // TODO: impl: atualizaCredito, atualizaDebito, deletaCredito, deletaDebito
}
