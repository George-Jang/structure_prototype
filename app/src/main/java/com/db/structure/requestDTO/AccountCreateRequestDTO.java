package com.db.structure.requestDTO;

import android.widget.EditText;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class AccountCreateRequestDTO {
    //TODO: LocalDate 로 변경
    private String birthday;

    private String address;

    private String accountType;

    private String name;

    private String phoneNum;

    private String job;

    private String accountPassword;

    private String userId;

    private String email;

    @Builder
    public AccountCreateRequestDTO(EditText birthday, EditText address,EditText accountType, EditText name
    ,EditText phoneNum, EditText job, EditText accountPassword, EditText userId, EditText email){

        this.birthday = birthday.getText().toString();
        this.address = address.getText().toString();
        this.accountType = accountType.getText().toString();
        this.name = name.getText().toString();
        this.phoneNum = phoneNum.getText().toString();
        this.job = job.getText().toString();
        this.accountPassword = accountPassword.getText().toString();
        this.userId = userId.getText().toString();
        this.email = email.getText().toString();
    }
}
