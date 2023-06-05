package com.cristiancmello.cashflower.data;

import com.cristiancmello.cashflower.gateway.CreditoDsRequestModel;
import com.cristiancmello.cashflower.gateway.DebitoDsRequestModel;
import com.cristiancmello.cashflower.gateway.LancamentoContabilDsResponseModel;
import com.cristiancmello.cashflower.gateway.RegistraLancamentoContabilDsGateway;
import org.springframework.stereotype.Component;

@Component
public class JpaLancamentoContabil implements RegistraLancamentoContabilDsGateway {
    final LancamentoCreditoRepository lancamentoCreditoRepository;

    final LancamentoDebitoRepository lancamentoDebitoRepository;

    public JpaLancamentoContabil(LancamentoCreditoRepository lancamentoCreditoRepository,
                                 LancamentoDebitoRepository lancamentoDebitoRepository)
    {
        this.lancamentoCreditoRepository = lancamentoCreditoRepository;
        this.lancamentoDebitoRepository = lancamentoDebitoRepository;
    }

    @Override
    public LancamentoContabilDsResponseModel save(CreditoDsRequestModel requestModel) {
        var creditoMapper = CreditoDataMapper.builder()
            .valor(requestModel.getValor())
            .dataEHora(requestModel.getDataEHoraDeRegistro())
            .build();

        var saved = lancamentoCreditoRepository.save(creditoMapper);

        return new LancamentoContabilDsResponseModel(saved.getId(), saved.getValor(), saved.getDataEHora());
    }

    @Override
    public LancamentoContabilDsResponseModel save(DebitoDsRequestModel requestModel) {
        var creditoMapper = DebitoDataMapper.builder()
            .valor(requestModel.getValor())
            .dataEHora(requestModel.getDataEHoraDeRegistro())
            .build();

        var saved = lancamentoDebitoRepository.save(creditoMapper);

        return new LancamentoContabilDsResponseModel(saved.getId(), saved.getValor(), saved.getDataEHora());
    }
}
