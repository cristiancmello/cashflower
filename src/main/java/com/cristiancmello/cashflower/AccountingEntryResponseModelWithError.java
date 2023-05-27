package com.cristiancmello.cashflower;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class AccountingEntryResponseModelWithError extends AbstractAccountingEntryResponseModel {
  private final Map<String, String> fields;

  @Builder
  public AccountingEntryResponseModelWithError(String message, Map<String, String> fields) {
    super(message);

    this.fields = fields;
  }
}