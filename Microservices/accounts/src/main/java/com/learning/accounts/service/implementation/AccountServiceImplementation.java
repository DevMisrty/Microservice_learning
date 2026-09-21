package com.learning.accounts.service.implementation;

import com.learning.accounts.dto.request.AddCustomerToAccountRequestDto;
import com.learning.accounts.dto.request.CreateAccountRequestDto;
import com.learning.accounts.dto.response.AccountResponseDto;
import com.learning.accounts.exception.ResourceNotFoundException;
import com.learning.accounts.model.Account;
import com.learning.accounts.model.Customer;
import com.learning.accounts.respositiory.AccountRepository;
import com.learning.accounts.respositiory.CustomerRepo;
import com.learning.accounts.service.AccountService;
import com.learning.accounts.utility.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AccountServiceImplementation implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepo customerRepo;

    @Override
    @Transactional
    public AccountResponseDto createAccount(CreateAccountRequestDto requestDto) {
        Customer customer = customerRepo.findById(requestDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "ID", String.valueOf(requestDto.getCustomerId())));

        Account account = Account.builder()
                .accountNumber(generateUniqueAccountNumber())
                .accountType(Account.AccountType.SAVINGS)
                .balance(1000L)
                .branchName("ABC")
                .build();

        // Add bidirectional relationship using mutable sets
        account.getCustomers().add(customer);
        customer.getAccounts().add(account);

        Account savedAccount = accountRepository.save(account);
        customerRepo.save(customer);

        return AccountMapper.toAccountResponseDto(savedAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponseDto getAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountNumber));

        return AccountMapper.toAccountResponseDto(account);
    }

    @Override
    @Transactional
    public AccountResponseDto addCustomerToAccount(String accountNumber, AddCustomerToAccountRequestDto requestDto) {
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber", accountNumber));

        Customer customer = customerRepo.findById(requestDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "ID", String.valueOf(requestDto.getCustomerId())));

        // Check if customer is already linked to this account
        if (account.getCustomers().contains(customer)) {
            throw new IllegalArgumentException("Customer is already linked to this account");
        }

        // Add bidirectional relationship
        account.getCustomers().add(customer);
        customer.getAccounts().add(account);

        accountRepository.save(account);
        customerRepo.save(customer);

        return AccountMapper.toAccountResponseDto(account);
    }

    private String generateUniqueAccountNumber() {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String prefix = "ACC-" + datePart + "-";

        long dailyCount = accountRepository.countByAccountNumberStartingWith(prefix);
        String sequence = String.format("%03d", dailyCount + 1);

        return prefix + sequence;
    }
}
