package com.learning.accounts.service.client;

import com.learning.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("cardsService")
public interface CardsFeignClient {

    @GetMapping(value = "/api/config", consumes = "application/json")
    ResponseEntity<Map<String, Object>> getCardsConfig();

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    ResponseEntity<CardsDto> fetchCardDetails(@RequestParam("mobileNumber") String mobileNumber);

}