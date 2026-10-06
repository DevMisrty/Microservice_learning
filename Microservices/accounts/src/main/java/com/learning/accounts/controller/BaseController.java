package com.learning.accounts.controller;

import com.learning.accounts.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BaseController {


    @GetMapping("/hello")
    public ResponseEntity<ResponseDto<String>> hello() {
        log.info("Hello from Accounts Service");
        return ResponseDto.success("Hello from Accounts Service");
    }

}
