package com.cristiancmello.cashflower;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractLedgerResponseModel {
  private String message;

  private String status;
}
