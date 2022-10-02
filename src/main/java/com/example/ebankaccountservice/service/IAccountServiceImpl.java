package com.example.ebankaccountservice.service;

import com.example.ebankaccountservice.DTO.AccountRequestDTO;
import com.example.ebankaccountservice.DTO.AccountResponseDTO;
import com.example.ebankaccountservice.entities.Account;
import com.example.ebankaccountservice.repositories.IBankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class IAccountServiceImpl implements IAccountService {
    @Autowired
    IBankAccountRepository iBankAccountRepository;

    @Override
    public AccountResponseDTO addAccount(AccountRequestDTO accountDTO) {

        Account account = Account.builder().
                id(UUID.randomUUID().toString()).
                 createdAt(new Date()).
                balance(accountDTO.getBalance()).
                type(accountDTO.getType()).
                currency(accountDTO.getCurrency()).
                build();

        Account savedAccount = iBankAccountRepository.save(account);

        AccountResponseDTO accountResponseDTO = AccountResponseDTO.builder().
                id(savedAccount.getId()).
                createdAt(savedAccount.getCreatedAt()).
                balance(savedAccount.getBalance()).
                type(savedAccount.getType()).
                currency(savedAccount.getCurrency())
                .build();


        return accountResponseDTO;
    }

    @Override
    public AccountResponseDTO updateAccount(AccountRequestDTO accountDTO, String id) {

        Account account = Account.builder().
                id(id).
                createdAt(new Date()).
                balance(accountDTO.getBalance()).
                type(accountDTO.getType()).
                currency(accountDTO.getCurrency()).
                build();

        Account savedAccount = iBankAccountRepository.save(account);

        AccountResponseDTO accountResponseDTO = AccountResponseDTO.builder().
                id(savedAccount.getId()).
                createdAt(savedAccount.getCreatedAt()).
                balance(savedAccount.getBalance()).
                type(savedAccount.getType()).
                currency(savedAccount.getCurrency())
                .build();


        return accountResponseDTO;
    }

    @Override
    public Boolean deleteAccount(String id) {
        iBankAccountRepository.deleteById(id);
        return true;
    }
}
