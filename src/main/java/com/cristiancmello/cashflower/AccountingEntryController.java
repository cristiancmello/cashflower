package com.cristiancmello.cashflower;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
public class AccountingEntryController {
  @PostMapping(path = "/accounting-entry", consumes = "application/json", produces = "application/json")
  ResponseEntity<Object> create(@RequestBody AccountingEntryRequestModel requestModel) {
    var isTypeNullOrBlank = requestModel.getType() == null || requestModel.getType().isBlank();
    var isValueNullableOrBlank = requestModel.getValue() == null || requestModel.getValue().isBlank();
    var isRequestFieldsNullableOrBlank = isTypeNullOrBlank || isValueNullableOrBlank;

    if (isRequestFieldsNullableOrBlank) {
      Map<String, String> fields = new java.util.HashMap<>(Map.of());

      if (isTypeNullOrBlank) {
        fields.put("type", "Is Blank");
      }

      if (isValueNullableOrBlank) {
        fields.put("value", "Is Blank");
      }

      var messageCause = "";

      if (fields.size() == 1) messageCause = "Empty field";
      else messageCause = "Empty fields";

      var responseErrorBuilder = AccountingEntryResponseModelWithError.builder()
        .fields(fields)
        .message(messageCause)
        .build();

      return ResponseEntity.badRequest().body(responseErrorBuilder);
    }

    var location = ServletUriComponentsBuilder.fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand("123")
      .toUri();

    var stringFromUuid = String.valueOf(UUID.randomUUID());

    var responseBody = AccountingEntryResponseModel.builder()
      .message("Successfully created")
      .id(stringFromUuid)
      .creationTime(String.valueOf(LocalDateTime.now().withNano(0)))
      .build();

    return ResponseEntity.created(location).body(responseBody);
  }
}
