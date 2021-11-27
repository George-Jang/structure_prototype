package com.db.structure.requestDTO;

import com.db.structure.responseDTO.CardType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CardCreateRequestDTO {
    private Long accountId;

    private Long cardLimit;

    private CardType cardType;
}
