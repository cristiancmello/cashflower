package com.cristiancmello.cashflower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountingEntryControllerIT {
  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int randomPort;

  private URI uri;

  private HttpHeaders headers;

  @BeforeEach
  public void setUp() throws URISyntaxException {
    var baseUrl = "http://localhost:" + randomPort + "/accounting-entry";
    uri = new URI(baseUrl);

    headers = new HttpHeaders();

    headers.setContentType(MediaType.APPLICATION_JSON);
  }

  @Test
  void givenAccountingEntry_whenCreate_thenResponseSuccessfully() {
    var requestModel = AccountingEntryRequestModel.builder()
      .type("CREDIT")
      .value("120.0")
      .build();

    var request = new HttpEntity<>(requestModel, headers);

    var response = restTemplate.postForEntity(uri, request, AccountingEntryResponseModelSuccessfully.class);

    var nonNullBody = Objects.requireNonNull(response.getBody());

    assertThat(response.getStatusCode().value()).isEqualTo(201);
    assertThat(nonNullBody.getMessage()).containsIgnoringCase("successfully created");
    assertThat(UUID.fromString(nonNullBody.getId())).isInstanceOf(UUID.class);
    assertThat(LocalDateTime.parse(nonNullBody.getCreationTime())).isInstanceOf(LocalDateTime.class);
    assertThat(nonNullBody.getStatus()).isEqualTo("success");
  }

  @Test
  void givenAccountingEntryWithEmptyFields_whenCreate_thenResponseWithError() {
    var requestModel = AccountingEntryRequestModel.builder()
      .build();

    var request = new HttpEntity<>(requestModel, headers);

    var response = restTemplate.postForEntity(uri, request, AccountingEntryResponseModelWithError.class);

    var nonNullBody = Objects.requireNonNull(response.getBody());

    assertThat(response.getStatusCode().value()).isEqualTo(400);
    assertThat(nonNullBody.getStatus()).isEqualTo("error");
    assertThat(nonNullBody.getMessage()).containsIgnoringCase("empty fields");
  }
}
