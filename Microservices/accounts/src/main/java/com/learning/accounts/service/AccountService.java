package com.learning.accounts.service;

import com.learning.accounts.dto.request.AddCustomerToAccountRequestDto;
import com.learning.accounts.dto.request.CreateAccountRequestDto;
import com.learning.accounts.dto.response.AccountResponseDto;
import jakarta.validation.Valid;

public interface AccountService {
    AccountResponseDto createAccount(@Valid CreateAccountRequestDto requestDto);
    AccountResponseDto getAccountByAccountNumber(String accountNumber);
    AccountResponseDto addCustomerToAccount(String accountNumber, @Valid AddCustomerToAccountRequestDto requestDto);
}
