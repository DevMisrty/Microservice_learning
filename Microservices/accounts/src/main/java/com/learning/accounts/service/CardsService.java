package com.learning.accounts.service;

import com.learning.accounts.dto.CardsDto;

public interface CardsService {

    CardsDto fetchCardDetails(String mobileNumber);

}