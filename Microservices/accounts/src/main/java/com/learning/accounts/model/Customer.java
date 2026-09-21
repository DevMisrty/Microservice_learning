package com.learning.accounts.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString(exclude = "accounts")
@EqualsAndHashCode(callSuper = true, exclude = "accounts")
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;
    private String email;
    private String phoneNumber;

    @ManyToMany()
    @JoinTable(
            name = "customer_account",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "account_number")
    )
    @Builder.Default
    private Set<Account> accounts = new HashSet<>();
}
