package com.cristiancmello.cashflower;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class LedgerController {
  @PostMapping(path = "/ledger", consumes = "application/json", produces = "application/json")
  ResponseEntity<AbstractLedgerResponseModel> create(@RequestBody LedgerRequestModel requestModel) {
    var isTitleNullableOrBlank = requestModel.getTitle() == null || requestModel.getTitle().isBlank();
    var isDescriptionNullableOrBlank = requestModel.getDescription() == null || requestModel.getDescription().isBlank();
    var isRequestFieldsNullableOrBlank = isTitleNullableOrBlank || isDescriptionNullableOrBlank;

    if (isRequestFieldsNullableOrBlank) {
      Map<String, String> fields = new HashMap<>();

      if (isTitleNullableOrBlank) fields.put("title", "Is Blank");
      if (isDescriptionNullableOrBlank) fields.put("description", "Is Blank");

      var responseModel = LedgerResponseModelWithError.builder()
        .status("error")
        .fields(fields)
        .message("Empty Fields")
        .build();

      return ResponseEntity.badRequest().body(responseModel);
    }

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
