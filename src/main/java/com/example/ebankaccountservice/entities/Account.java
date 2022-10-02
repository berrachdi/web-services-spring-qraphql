package com.example.ebankaccountservice.entities;

import com.example.ebankaccountservice.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "accounts")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {

    @Id
    private String id;
    private Date createdAt;
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
