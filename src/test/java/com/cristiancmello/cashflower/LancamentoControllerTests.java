package com.cristiancmello.cashflower;

import com.cristiancmello.cashflower.domain.usecase.LancamentoCreditoRequest;
import com.cristiancmello.cashflower.domain.usecase.LancamentoDebitoRequest;
import com.cristiancmello.cashflower.infra.ConsolidadoFeignClient;
import com.cristiancmello.cashflower.presentation.LancamentoContabilResponseModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LancamentoControllerTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ConsolidadoFeignClient consolidadoFeignClient;

    @Test
    public void deveLancarCreditoComSucesso() {
        var url = "http://localhost:%d/lancamento/credito".formatted(port);
        var headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        var lancamentoCredito = LancamentoCreditoRequest.builder()
            .valor("300.00")
            .dataEHora(LocalDateTime.now().toString())
            .build();

        var requestEntity = new HttpEntity<>(lancamentoCredito, headers);
        var response = restTemplate.postForObject(url, requestEntity, LancamentoContabilResponseModel.class);

        assertThat(response.getMensagem()).contains("sucesso");
        assertThat(response.getDataEHoraLancamento()).contains(
            LocalDateTime.now()
                .withNano(0)
                .withSecond(0)
                .toString()
        );
    }

    @Test
    public void deveLancarDebitoComSucesso() {
        var url = "http://localhost:%d/lancamento/debito".formatted(port);
        var headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        var lancamentoDebito = LancamentoDebitoRequest.builder()
            .valor("100.00")
            .dataEHora(LocalDateTime.now().toString())
            .build();

        var requestEntity = new HttpEntity<>(lancamentoDebito, headers);
        var response = restTemplate.postForObject(url, requestEntity, LancamentoContabilResponseModel.class);

        assertThat(response.getMensagem()).contains("sucesso");
        assertThat(response.getDataEHoraLancamento()).contains(
            LocalDateTime.now()
                .withNano(0)
                .withSecond(0)
                .toString()
        );
    }

    @Test
    public void deveLancarCreditoCampoFormatadoErroComFalha() {
        var url = "http://localhost:%d/lancamento/credito".formatted(port);
        var headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        var lancamentoCredito = LancamentoCreditoRequest.builder()
            .valor("ABC")
            .build();

        var requestEntity = new HttpEntity<>(lancamentoCredito, headers);
        var response = restTemplate.postForObject(url, requestEntity, String.class);

        assertThat(response).contains(List.of("400", "Bad Request"));
    }
}
