package com.learning.accounts.service.implementation;

import com.learning.accounts.dto.CardsDto;
import com.learning.accounts.exception.ResourceNotFoundException;
import com.learning.accounts.respositiory.CustomerRepo;
import com.learning.accounts.service.CardsService;
import com.learning.accounts.service.client.CardsFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardsServiceImplementation implements CardsService {

    private final CustomerRepo customerRepo;
    private final CardsFeignClient cardsFeignClient;

    @Override
    public CardsDto fetchCardDetails(String mobileNumber) {
        // Ensure the customer exists before delegating to the cards service
        customerRepo.findByPhoneNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "phoneNumber", mobileNumber));

        ResponseEntity<CardsDto> response = cardsFeignClient.fetchCardDetails(mobileNumber);

        if (response == null || response.getBody() == null) {
            throw new ResourceNotFoundException("Cards", "mobileNumber", mobileNumber);
        }

        return response.getBody();
    }
}