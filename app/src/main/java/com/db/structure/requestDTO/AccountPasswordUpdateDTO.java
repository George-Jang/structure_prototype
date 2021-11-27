package com.db.structure.requestDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountPasswordUpdateDTO {
    private String password;
}
