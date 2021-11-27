package com.db.structure.responseDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CardListResponse {
    private List<CardResponse> cardResponses;

    private AccountResponse accountResponse;
}
