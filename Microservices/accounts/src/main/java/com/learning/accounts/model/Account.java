package com.learning.accounts.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString(exclude = "customers")
@EqualsAndHashCode(callSuper = true, exclude = "customers")
public class Account extends BaseEntity {

    @Id
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private Long balance;
    private String branchName;

    @ManyToMany(mappedBy = "accounts")
    @Builder.Default
    private Set<Customer> customers = new HashSet<>();

    public enum AccountType{
        SAVINGS, CURRENT, FIXED_DEPOSIT, SALARY, RECURRING
    }
}
