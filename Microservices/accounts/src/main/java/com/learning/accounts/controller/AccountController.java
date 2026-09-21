package com.learning.accounts.controller;

import com.learning.accounts.dto.ResponseDto;
import com.learning.accounts.dto.request.AddCustomerToAccountRequestDto;
import com.learning.accounts.dto.request.CreateAccountRequestDto;
import com.learning.accounts.dto.response.AccountResponseDto;
import com.learning.accounts.service.AccountService;
import com.learning.accounts.utility.Message;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final ConfigurableEnvironment environment;

    @GetMapping("/config")
    public ResponseEntity<ResponseDto<Map<String, Object>>> getConfig() {
        Map<String, Object> propertySources = new LinkedHashMap<>();
        for (PropertySource<?> source : environment.getPropertySources()) {
            propertySources.put(
                    source.getName(),
                    source.getSource() != null ? source.getSource().getClass().getSimpleName() : null
            );
        }

        Map<String, Object> config = new LinkedHashMap<>();
        config.put("appName", environment.getProperty("spring.application.name", "accounts"));
        config.put("activeProfiles", environment.getActiveProfiles());
        config.put("defaultProfiles", environment.getDefaultProfiles());
        config.put("message", environment.getProperty("message", "NOT SET"));
        config.put("serverPort", environment.getProperty("server.port", "NOT SET"));
        config.put("propertySources", propertySources);

        return ResponseDto.success(config);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<AccountResponseDto>> createAccount(@Valid @RequestBody CreateAccountRequestDto requestDto) {
        AccountResponseDto responseDto = accountService.createAccount(requestDto);
        return ResponseDto.created(responseDto, Message.AccountConstants.ACCOUNT_CREATED);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<ResponseDto<AccountResponseDto>> getAccount(@PathVariable String accountNumber) {
        AccountResponseDto responseDto = accountService.getAccountByAccountNumber(accountNumber);
        return ResponseDto.success(responseDto, Message.AccountConstants.ACCOUNT_FETCHED);
    }

    @PutMapping("/{accountNumber}/add-customer")
    public ResponseEntity<ResponseDto<AccountResponseDto>> addCustomerToAccount(
            @PathVariable String accountNumber,
            @Valid @RequestBody AddCustomerToAccountRequestDto requestDto) {
        AccountResponseDto responseDto = accountService.addCustomerToAccount(accountNumber, requestDto);
        return ResponseDto.success(responseDto, Message.AccountConstants.ACCOUNT_UPDATED);
    }

}
