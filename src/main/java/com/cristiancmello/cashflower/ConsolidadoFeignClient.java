package com.cristiancmello.cashflower;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ConsolidadoFeignClient", url = "http://localhost:8081")
public interface ConsolidadoFeignClient {
    @PostMapping(value = "consolidado-diario", produces = "application/json")
    String registraConsolidadoDiario(@RequestBody RegistraConsolidadoRequest requestBody);
}
