package com.cristiancmello.cashflower;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class LedgerResponseModelWithError extends AbstractLedgerResponseModel {
  private final Map<String, String> fields;

  @Builder
  public LedgerResponseModelWithError(String message, String status, Map<String, String> fields) {
    super(message, status);

    this.fields = fields;
  }
}
