package com.cristiancmello.cashflower;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractAccountingEntryResponseModel {
  private String message;
}
