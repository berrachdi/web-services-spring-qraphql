package com.example.ebankaccountservice;

import com.example.ebankaccountservice.entities.Account;
import com.example.ebankaccountservice.entities.Customer;
import com.example.ebankaccountservice.enums.AccountType;
import com.example.ebankaccountservice.repositories.IBankAccountRepository;
import com.example.ebankaccountservice.repositories.ICustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankAccountServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner (
            IBankAccountRepository iBankAccountRepository,
            ICustomerRepository iCustomerRepository
    ){
        return args -> {

            Stream.of("AHMED","MOHAMED","KARIM","AMIRA").forEach(c -> {
                Customer customer = Customer.builder().
                name(c).build();

                iCustomerRepository.save(customer);
            } );


            iCustomerRepository.findAll().forEach(c->{

                for(int i = 0; i < 3 ; i++){

                    Account bankAccount = Account.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                            .balance(Math.random()*4000 + 2000)
                            .currency("MAD")
                            .createdAt(new Date())
                            .customer(c)
                            .build();


                    iBankAccountRepository.save(bankAccount);
                }

            });

        };
    }
}
