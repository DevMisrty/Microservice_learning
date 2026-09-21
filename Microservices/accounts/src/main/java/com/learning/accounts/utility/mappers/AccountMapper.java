package com.learning.accounts.utility.mappers;

import com.learning.accounts.dto.response.AccountResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    public static AccountResponseDto toAccountResponseDto(com.learning.accounts.model.Account account) {
        return AccountResponseDto.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .Balance(account.getBalance())
                .branchName(account.getBranchName())
                .accountOwners(
                        account.getCustomers().stream()
                        .map(CustomerMapper::toCustomerResponseDto)
                        .toList())
                .build();
    }
}
