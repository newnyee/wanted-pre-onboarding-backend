package com.preonboarding.controller;
import com.preonboarding.dto.RequestEmployRegistrationDto;
import com.preonboarding.dto.ResponseSavedEmployDto;
import com.preonboarding.service.EmployService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/employ")
public class EmployController {

    private final EmployService employService;

    /**
     * 채용공고 등록 API
     * @param  requestEmployRegistrationDto 등록할 채용공고 정보
     * @return 등록된 채용공고 정보
     */
    @PostMapping
    public ResponseEntity signUp(@RequestBody RequestEmployRegistrationDto requestEmployRegistrationDto) {
        ResponseSavedEmployDto responseSavedEmployDto = employService.employInsert(requestEmployRegistrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseSavedEmployDto);
    }
}
