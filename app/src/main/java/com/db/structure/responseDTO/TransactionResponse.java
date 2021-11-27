package com.db.structure.responseDTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TransactionResponse {
    private Long id;

    private String transactionDate;

    private AccountResponse sender;

    private String type;

    private String comment;

    private Long transactionValue;

    private AccountResponse receiver;
}
