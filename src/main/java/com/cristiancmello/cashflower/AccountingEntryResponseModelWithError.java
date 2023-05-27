package com.cristiancmello.cashflower;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@Builder
@ToString
public class AccountingEntryResponseModelWithError {
    private String message;
    private Map<String, String> fields;
}