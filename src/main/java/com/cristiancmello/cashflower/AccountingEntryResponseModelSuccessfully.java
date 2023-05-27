package com.cristiancmello.cashflower;

import lombok.*;

@Getter
public class AccountingEntryResponseModelSuccessfully extends AbstractAccountingEntryResponseModel {
  private final String id;

  private final String creationTime;

  @Builder
  public AccountingEntryResponseModelSuccessfully(String message, String status, String id, String creationTime) {
    super(message, "success");

    this.id = id;
    this.creationTime = creationTime;
  }
}
