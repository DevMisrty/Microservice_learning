package com.learning.accounts.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CustomerResponseDto {

    private Long customerId;
    private String name;
    private String email;
    private String phoneNumber;

}
