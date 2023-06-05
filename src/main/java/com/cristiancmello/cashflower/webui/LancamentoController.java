package com.cristiancmello.cashflower.webui;

import com.cristiancmello.cashflower.presentation.LancamentoContabilResponseModel;
import com.cristiancmello.cashflower.domain.usecase.LancamentoContabilUseCase;
import com.cristiancmello.cashflower.domain.usecase.LancamentoCreditoRequest;
import com.cristiancmello.cashflower.domain.usecase.LancamentoDebitoRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "lancamento")
@AllArgsConstructor
public class LancamentoController {
    final LancamentoContabilUseCase lancamentoContabilUseCase;

    @PostMapping("debito")
    LancamentoContabilResponseModel lancaDebito(@RequestBody LancamentoDebitoRequest request) {
        return lancamentoContabilUseCase.lancaDebito(request);
    }

    @PostMapping("credito")
    LancamentoContabilResponseModel lancaCredito(@RequestBody LancamentoCreditoRequest request) {
        return lancamentoContabilUseCase.lancaCredito(request);
    }
}
