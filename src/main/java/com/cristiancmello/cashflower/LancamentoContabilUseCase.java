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
    public LancamentoContabilResponseModel lanca(LancamentoContabilRequestModel request, TipoMovimentacao tipoMovimentacao) {
        LancamentoContabil lancamento;

        var dataEHoraDeRegistro = LocalDateTime.now();
        var valor = BigDecimal.ZERO;

        if (request.getValor() == null) {
            return lancamentoContabilPresenter.prepareFailView("Campo valor nao pode ser vazio.");
        }

        try {
            valor = BigDecimal.valueOf(Double.parseDouble(request.getValor()));
        } catch (NumberFormatException e) {
            return lancamentoContabilPresenter.prepareFailView("Valor inserido deve ser decimal. Ex. 200.10");
        }

        var lancamentoContabil = new LancamentoContabil(valor, dataEHoraDeRegistro);

        String lancamentoId;

        switch (tipoMovimentacao) {
            case CREDITO -> {
                lancamento = lancamentoCreditoRepository.save(Credito.builder()
                    .valor(lancamentoContabil.getValor())
                    .dataEHora(lancamentoContabil.getDataEHora())
                    .build());

                lancamentoId = String.valueOf(((Credito) lancamento).getId());
            }
            case DEBITO -> {
                lancamento = lancamentoDebitoRepository.save(Debito.builder()
                    .valor(lancamentoContabil.getValor())
                    .dataEHora(lancamentoContabil.getDataEHora())
                    .build()
                );

                lancamentoId = String.valueOf(((Debito) lancamento).getId());
            }
            default -> {
                return lancamentoContabilPresenter.prepareFailView("Somente operacoes de Credito ou Debito sao aceitos.");
            }
        }

        var registroConsolidadoRequest = RegistraConsolidadoRequest.builder()
            .valor(lancamento.getValor().toString())
            .tipoMovimentacao(String.valueOf(tipoMovimentacao))
            .dataHoraLancamento(lancamento.getDataEHora().toString())
            .lancamentoId(lancamentoId)
            .build();

        consolidadoFeignClient.registraConsolidadoDiario(registroConsolidadoRequest);

        var lancamentoContabilResponseModel = LancamentoContabilResponseModel.builder()
            .dataEHoraLancamento(String.valueOf(dataEHoraDeRegistro))
            .build();

        return lancamentoContabilPresenter.prepareSuccessView(lancamentoContabilResponseModel);
    }

    @Override
    public LancamentoContabilResponseModel lancaCredito(LancamentoCreditoRequest request) {
        return lanca(request, TipoMovimentacao.CREDITO);
    }

    @Override
    public LancamentoContabilResponseModel lancaDebito(LancamentoDebitoRequest request) {
        return lanca(request, TipoMovimentacao.DEBITO);
    }
}
