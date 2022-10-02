package com.example.ebankaccountservice.web;

import com.example.ebankaccountservice.DTO.AccountRequestDTO;
import com.example.ebankaccountservice.DTO.AccountResponseDTO;
import com.example.ebankaccountservice.entities.Account;
import com.example.ebankaccountservice.repositories.IBankAccountRepository;
import com.example.ebankaccountservice.service.IAccountService;
import com.example.ebankaccountservice.service.IAccountServiceImpl;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountRest {

    @Autowired
    IBankAccountRepository iBankAccountRepository;

    @Autowired
    IAccountServiceImpl iAccountService;


    @GetMapping("/bankaccounts")
    public List<Account> getAccounts(){
        return iBankAccountRepository.findAll();
    }
    @GetMapping("/bankaccounts/{id}")
    public Account getAccounts(@PathVariable String id){
        return iBankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account of id: %s not found",id)));
    }

    @PostMapping("/bankaccounts")
    public AccountResponseDTO addAccounts(@RequestBody AccountRequestDTO account){
        return iAccountService.addAccount(account);

    }

    @PutMapping("/bankaccounts/{id}")
    public Account updateAccounts(@RequestBody Account updatedAccount,@PathVariable String id){

        Account bankAccount = iBankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account of id: %s not found",id)));

        if(updatedAccount.getBalance() != null) bankAccount.setBalance(updatedAccount.getBalance());
        if(updatedAccount.getCreatedAt() != null) bankAccount.setCreatedAt(updatedAccount.getCreatedAt());
        if(updatedAccount.getCurrency() != null) bankAccount.setCurrency(updatedAccount.getCurrency());
        if(updatedAccount.getType() != null) bankAccount.setType(updatedAccount.getType());

        return iBankAccountRepository.save(bankAccount);

    }

    @DeleteMapping("/bankaccounts/{id}")
    public void deleteAccounts(@PathVariable String id){

         iBankAccountRepository.deleteById(id);

    }
}
