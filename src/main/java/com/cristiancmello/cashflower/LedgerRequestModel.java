package com.cristiancmello.cashflower;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class LedgerRequestModel {
  private String title;

  private String description;
}
