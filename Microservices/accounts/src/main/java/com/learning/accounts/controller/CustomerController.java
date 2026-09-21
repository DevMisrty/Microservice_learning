package com.learning.accounts.controller;

import com.learning.accounts.dto.CustomerDto;
import com.learning.accounts.dto.ResponseDto;
import com.learning.accounts.dto.response.CustomerResponseDto;
import com.learning.accounts.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto<CustomerResponseDto>> createCustomer(@Valid  @RequestBody CustomerDto customerDto) {
        CustomerResponseDto createdCustomer = customerService.createCustomerAccount(customerDto);
        return ResponseDto.created(createdCustomer);
    }
}
