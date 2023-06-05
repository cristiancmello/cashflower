package com.cristiancmello.cashflower.infra;

import com.cristiancmello.cashflower.gateway.RegistraConsolidadoGateway;
import com.cristiancmello.cashflower.gateway.RegistraConsolidadoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ConsolidadoFeignClient", url = "${consolidadodiario.url}")
public interface ConsolidadoFeignClient extends RegistraConsolidadoGateway {
    @PostMapping(value = "consolidado-diario", produces = "application/json")
    String registraConsolidadoDiario(@RequestBody RegistraConsolidadoRequest requestBody);
}
