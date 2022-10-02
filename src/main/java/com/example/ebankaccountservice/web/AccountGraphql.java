package com.example.ebankaccountservice.web;

import com.example.ebankaccountservice.DTO.AccountDTO;
import com.example.ebankaccountservice.DTO.AccountRequestDTO;
import com.example.ebankaccountservice.DTO.AccountResponseDTO;
import com.example.ebankaccountservice.entities.Account;
import com.example.ebankaccountservice.entities.Customer;
import com.example.ebankaccountservice.repositories.IBankAccountRepository;
import com.example.ebankaccountservice.repositories.ICustomerRepository;
import com.example.ebankaccountservice.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Controller
public class AccountGraphql {

    @Autowired
    IBankAccountRepository iBankAccountRepository;

    @Autowired
    ICustomerRepository iCustomerRepository;

    @Autowired
    IAccountService iAccountService;

    @QueryMapping
    public List<Account> accountsList(){

        return iBankAccountRepository.findAll();
    }

    @QueryMapping
    public Account accountById(@Argument String id){
        return iBankAccountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account for id: %s not found",id)));

    }

    @MutationMapping
    public AccountResponseDTO addAccount(@Argument AccountRequestDTO account){
        return iAccountService.addAccount(account);


    }

    @MutationMapping
    public AccountResponseDTO updateAccounts(@Argument AccountRequestDTO updatedAccount, @Argument String id){

            return iAccountService.updateAccount(updatedAccount,id);



    }

    @MutationMapping
    public Boolean deleteAccounts(@Argument String id){

        return iAccountService.deleteAccount(id);


    }

    @QueryMapping
    public List<Customer> customersList(){
        return iCustomerRepository.findAll();
    }


}


