package com.db.structure.responseDTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class CardResponse {
    private Long cardId;

    private LocalDate cardIssueDate;

    private Long cardLimit;

    private CardType cardType;

    private AccountResponse accountResponse;
}
