package com.learning.accounts.dto.response;

import com.learning.accounts.model.Account;
import com.learning.accounts.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponseDto {

    private String accountNumber;
    private Account.AccountType accountType;
    private Long Balance;
    private String branchName;
    private List<CustomerResponseDto> accountOwners;

}
