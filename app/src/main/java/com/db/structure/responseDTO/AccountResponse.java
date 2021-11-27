package com.db.structure.responseDTO;

import com.db.structure.responseDTO.UserResponse;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class AccountResponse {
    private Long id;

    private String type;

    private Long balance;

    private String accountIssueDate;

    private UserResponse user;

    private boolean cardIssued;
}
