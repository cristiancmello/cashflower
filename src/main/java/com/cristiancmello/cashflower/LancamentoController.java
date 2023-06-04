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
    LancamentoContabilInputBoundary lancamentoContabilInputBoundary;

    @PostMapping("debito")
    LancamentoContabilResponseModel lancaDebito(@RequestBody LancamentoDebitoRequest request) throws Exception {
        return lancamentoContabilInputBoundary.lancaDebito(request);
    }

    @PostMapping("credito")
    LancamentoContabilResponseModel lancaCredito(@RequestBody LancamentoCreditoRequest request) throws Exception {
        return lancamentoContabilInputBoundary.lancaCredito(request);
    }

    // TODO: impl: atualizaCredito, atualizaDebito, deletaCredito, deletaDebito
}
