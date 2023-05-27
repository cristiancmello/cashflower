package com.cristiancmello.cashflower;

import lombok.*;

@Getter
public class LedgerResponseModelSuccessfully extends AbstractLedgerResponseModel {
  private final String id;

  private final String creationTime;

  @Builder
  public LedgerResponseModelSuccessfully(String message, String status, String id, String creationTime) {
    super(message, status);

    this.id = id;
    this.creationTime = creationTime;
  }
}
