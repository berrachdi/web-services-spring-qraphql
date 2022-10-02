package com.example.ebankaccountservice.service;

import com.example.ebankaccountservice.DTO.AccountRequestDTO;
import com.example.ebankaccountservice.DTO.AccountResponseDTO;

public interface IAccountService {

    public AccountResponseDTO addAccount(AccountRequestDTO accountDTO);

    AccountResponseDTO updateAccount(AccountRequestDTO accountDTO, String id);

    Boolean deleteAccount(String id);
}
