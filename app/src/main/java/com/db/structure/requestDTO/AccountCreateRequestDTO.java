package com.db.structure.requestDTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AccountCreateRequestDTO {
    private LocalDate birthday;

    private String address;

    private String accountType;

    private String name;

    private String phoneNum;

    private String job;

    private String accountPassword;

    private String userId;

    private String email;
}
