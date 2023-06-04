package com.cristiancmello.cashflower;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class LancamentoContabilUseCase implements LancamentoContabilInputBoundary {
    @Autowired
    ConsolidadoFeignClient consolidadoFeignClient;

    @Autowired
    private LancamentoCreditoRepository lancamentoCreditoRepository;

    @Autowired
    private LancamentoDebitoRepository lancamentoDebitoRepository;

    @Autowired
    private LancamentoContabilPresenter lancamentoContabilPresenter;


    @Override
    public LancamentoContabilResponseModel lanca(LancamentoContabilRequestModel requestModel) {
        return null;
    }

    @Override
    public LancamentoContabilResponseModel lancaCredito(LancamentoCreditoRequest request) {
        // TODO: colocar uma transaction (se registraConsolidadoDiario, nao lance nada
        var lancamento = lancamentoCreditoRepository.save(Credito.builder()
            .dataEHora(LocalDateTime.now())
            .valor(BigDecimal.valueOf(Double.parseDouble(request.getValor())))
            .build());

        var registroConsolidadoRequest = RegistraConsolidadoRequest.builder()
            .valor(lancamento.getValor().toString())
            .tipoMovimentacao("CREDITO")
            .dataHoraLancamento(lancamento.getDataEHora().toString())
            .lancamentoId(lancamento.getId().toString())
            .build();

        consolidadoFeignClient.registraConsolidadoDiario(registroConsolidadoRequest);

        var lancamentoContabilResponseModel = LancamentoContabilResponseModel.builder()
            .id(lancamento.getId().toString())
            .dataEHoraLancamento(String.valueOf(LocalDateTime.now()))
            .build();

        return lancamentoContabilPresenter.prepareSuccessView(lancamentoContabilResponseModel);
    }

    @Override
    public LancamentoContabilResponseModel lancaDebito(LancamentoDebitoRequest request) {
        // TODO: colocar uma transaction (se registraConsolidadoDiario, nao lance nada
        var lancamento = lancamentoDebitoRepository.save(Debito.builder()
            .dataEHora(LocalDateTime.now())
            .valor(BigDecimal.valueOf(Double.parseDouble(request.getValor())))
            .build());

        var registroConsolidadoRequest = RegistraConsolidadoRequest.builder()
            .valor(lancamento.getValor().toString())
            .tipoMovimentacao("DEBITO")
            .dataHoraLancamento(lancamento.getDataEHora().toString())
            .lancamentoId(lancamento.getId().toString())
            .build();

        consolidadoFeignClient.registraConsolidadoDiario(registroConsolidadoRequest);

        var lancamentoContabilResponseModel = LancamentoContabilResponseModel.builder()
            .id(lancamento.getId().toString())
            .dataEHoraLancamento(String.valueOf(LocalDateTime.now()))
            .build();

        return lancamentoContabilPresenter.prepareSuccessView(lancamentoContabilResponseModel);
    }
}
