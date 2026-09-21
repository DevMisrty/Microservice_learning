package com.learning.accounts.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCustomerToAccountRequestDto {

    @NotNull(message = "Customer ID must not be null")
    private Long customerId;
}
