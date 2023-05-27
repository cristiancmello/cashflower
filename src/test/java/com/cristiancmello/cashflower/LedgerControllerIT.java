package com.cristiancmello.cashflower;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class LedgerControllerIT extends BaseController {
  public LedgerControllerIT() {
    resourceRootPath = "/ledger";
  }

  @Test
  void givenLedgerRequest_whenCreate_thenResponseSuccessfully() {
    var requestModel = LedgerRequestModel.builder()
      .title("My Ledger")
      .description("A Ledger")
      .build();

    var request = new HttpEntity<>(requestModel, headers);

    var response = restTemplate.postForEntity(uri, request, LedgerResponseModelSuccessfully.class);

    var nonNullBody = Objects.requireNonNull(response.getBody());

    assertThat(response.getStatusCode().value()).isEqualTo(201);
    assertThat(nonNullBody.getMessage()).containsIgnoringCase("successfully created");
    assertThat(UUID.fromString(nonNullBody.getId())).isInstanceOf(UUID.class);
    assertThat(LocalDateTime.parse(nonNullBody.getCreationTime())).isInstanceOf(LocalDateTime.class);
    assertThat(nonNullBody.getStatus()).isEqualTo("success");
  }

  @Test
  void givenLedgerRequestWithEmptyFields_whenCreate_thenResponseWithError() {
    var requestModel = LedgerRequestModel.builder()
      .build();

    var request = new HttpEntity<>(requestModel, headers);

    var response = restTemplate.postForEntity(uri, request, LedgerResponseModelWithError.class);

    var nonNullBody = Objects.requireNonNull(response.getBody());

    assertThat(response.getStatusCode().value()).isEqualTo(400);
    assertThat(nonNullBody.getStatus()).isEqualTo("error");
    assertThat(nonNullBody.getMessage()).containsIgnoringCase("empty fields");
    assertThat(nonNullBody.getFields()).contains(Map.entry("title", "Is Blank"));
    assertThat(nonNullBody.getFields()).contains(Map.entry("description", "Is Blank"));
  }
}
