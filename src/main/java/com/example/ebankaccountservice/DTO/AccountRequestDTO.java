package com.example.ebankaccountservice.DTO;

import com.example.ebankaccountservice.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRequestDTO {


    private Double balance;
    private String currency;
    private AccountType type;
}
