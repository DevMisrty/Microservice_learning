package com.learning.accounts.controller;

import com.learning.accounts.dto.CardsDto;
import com.learning.accounts.dto.ResponseDto;
import com.learning.accounts.service.CardsService;
import com.learning.accounts.utility.Message;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
@Validated
public class CardsController {

    private final CardsService cardsService;

    @GetMapping("/fetch")
    public ResponseEntity<ResponseDto<CardsDto>> fetchCardDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            String mobileNumber) {

        CardsDto cardsDto = cardsService.fetchCardDetails(mobileNumber);
        return ResponseDto.success(cardsDto, Message.CardsConstants.CARDS_FETCHED);
    }

}