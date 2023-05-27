package com.cristiancmello.cashflower;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class LedgerController {
  @PostMapping(path = "/ledger", consumes = "application/json", produces = "application/json")
  ResponseEntity<AbstractLedgerResponseModel> create(@RequestBody LedgerRequestModel requestModel) {
    var location = ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand("123")
      .toUri();

    var responseModel = LedgerResponseModelSuccessfully.builder()
      .id(String.valueOf(UUID.randomUUID().toString()))
      .message("Successfully Created")
      .creationTime(String.valueOf(LocalDateTime.now().withNano(0)))
      .status("success")
      .build();

    return ResponseEntity.created(location).body(responseModel);
  }
}
