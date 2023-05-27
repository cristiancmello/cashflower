package com.cristiancmello.cashflower;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AccountingEntryResponseModel {
  private String message;

  private String id;

  private String creationTime;


}
