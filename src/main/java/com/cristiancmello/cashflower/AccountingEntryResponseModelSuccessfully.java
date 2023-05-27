package com.cristiancmello.cashflower;

import lombok.*;

@Getter
public class AccountingEntryResponseModelSuccessfully extends AbstractAccountingEntryResponseModel {
  private final String id;

  private final String creationTime;

  @Builder
  public AccountingEntryResponseModelSuccessfully(String message, String id, String creationTime) {
    super(message);

    this.id = id;
    this.creationTime = creationTime;
  }
}
