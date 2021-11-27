package com.db.structure.responseDTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class CardResponse {
    private AccountResponse accountResponse;

    private Long cardId;

    private String cardIssueDate;

    private Long cardLimit;

    private CardType cardType;
}
