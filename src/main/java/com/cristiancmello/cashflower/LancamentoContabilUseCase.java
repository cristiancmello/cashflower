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
    public LancamentoContabilResponseModel lanca(LancamentoContabilRequestModel request, TipoMovimentacao tipoMovimentacao) throws Exception {
        LancamentoContabil lancamento = null;
        var dataEHoraDeRegistro = LocalDateTime.now();
        var valor = BigDecimal.valueOf(Double.parseDouble(request.getValor()));
        var lancamentoContabil = new LancamentoContabil(valor, dataEHoraDeRegistro);

        String lancamentoId;

        if (tipoMovimentacao == TipoMovimentacao.CREDITO) {
            lancamento = lancamentoCreditoRepository.save(Credito.builder()
                .valor(lancamentoContabil.getValor())
                .dataEHora(lancamentoContabil.getDataEHora())
                .build());

            lancamentoId = String.valueOf(((Credito) lancamento).getId());
        } else if (tipoMovimentacao == TipoMovimentacao.DEBITO) {
            lancamento = lancamentoDebitoRepository.save(Debito.builder()
                .valor(lancamentoContabil.getValor())
                .dataEHora(lancamentoContabil.getDataEHora())
                .build()
            );

            lancamentoId = String.valueOf(((Debito) lancamento).getId());
        } else {
            throw new Exception("Tipo Movimentacao precisa ser ou CREDITO ou DEBITO.");
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
    public LancamentoContabilResponseModel lancaCredito(LancamentoCreditoRequest request) throws Exception {
        return lanca(request, TipoMovimentacao.CREDITO);
    }

    @Override
    public LancamentoContabilResponseModel lancaDebito(LancamentoDebitoRequest request) throws Exception {
        return lanca(request, TipoMovimentacao.DEBITO);
    }
}
