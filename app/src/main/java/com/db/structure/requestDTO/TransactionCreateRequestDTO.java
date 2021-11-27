package com.db.structure.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class TransactionCreateRequestDTO {
    private String comment;
    private String sendingAccount;
    private Long transactionValue;
    private String type;
    private String receivingAccount;
}
