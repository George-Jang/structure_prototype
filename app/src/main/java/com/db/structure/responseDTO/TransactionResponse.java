package com.db.structure.responseDTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class TransactionResponse {
    private Long id;

    private String transactionDate;

    private String type;

    private String comment;

    private Long transactionValue;

    private AccountResponse client;

    private long balanceAfterTransaction;
}
