package com.cristiancmello.cashflower.domain.usecase;

import com.cristiancmello.cashflower.domain.entity.TipoMovimentacao;
import com.cristiancmello.cashflower.domain.factory.CreditoFactory;
import com.cristiancmello.cashflower.domain.factory.DebitoFactory;
import com.cristiancmello.cashflower.gateway.*;
import com.cristiancmello.cashflower.presentation.LancamentoContabilPresenter;
import com.cristiancmello.cashflower.presentation.LancamentoContabilRequestModel;
import com.cristiancmello.cashflower.presentation.LancamentoContabilResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LancamentoContabilUseCase implements LancamentoContabilInputBoundary {
    final RegistraConsolidadoGateway registraConsolidadoDiario;

    final LancamentoContabilPresenter lancamentoContabilPresenter;

    final CreditoFactory creditoFactory;

    final DebitoFactory debitoFactory;

    final RegistraLancamentoContabilDsGateway registraLancamentoDsGateway;

    @Override
    public LancamentoContabilResponseModel lanca(LancamentoContabilRequestModel request, TipoMovimentacao tipoMovimentacao) {
        var dataEHoraDeRegistro = LocalDateTime.now();
        var valor = BigDecimal.ZERO;

        if (request.getValor() == null) {
            return lancamentoContabilPresenter.prepareFailView("Campo getValor nao pode ser vazio.");
        }

        try {
            valor = BigDecimal.valueOf(Double.parseDouble(request.getValor()));
        } catch (NumberFormatException e) {
            return lancamentoContabilPresenter.prepareFailView("Valor inserido deve ser decimal. Ex. 200.10");
        }

        LancamentoContabilDsResponseModel dsResponseModel;

        switch (tipoMovimentacao) {
            case CREDITO -> {
                var creditoModel = creditoFactory.create(valor);
                var creditoDsRequestModel = new CreditoDsRequestModel(creditoModel.getValor(), dataEHoraDeRegistro);

                dsResponseModel = registraLancamentoDsGateway.save(creditoDsRequestModel);
            }
            case DEBITO -> {
                var debitoModel = debitoFactory.create(valor);
                var debitoDsRequestModel = new DebitoDsRequestModel(debitoModel.getValor(), dataEHoraDeRegistro);

                dsResponseModel = registraLancamentoDsGateway.save(debitoDsRequestModel);
            }
            default -> {
                return lancamentoContabilPresenter.prepareFailView("Somente operacoes de CreditoDataMapper ou DebitoDataMapper sao aceitos.");
            }
        }

        var registroConsolidadoRequest = RegistraConsolidadoRequest.builder()
            .valor(String.valueOf(dsResponseModel.getValor()))
            .tipoMovimentacao(String.valueOf(tipoMovimentacao))
            .dataHoraLancamento(dsResponseModel.getDataEHora().toString())
            .lancamentoId(String.valueOf(dsResponseModel.getId()))
            .build();

        registraConsolidadoDiario.registraConsolidadoDiario(registroConsolidadoRequest);

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
