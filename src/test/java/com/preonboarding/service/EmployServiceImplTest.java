package com.preonboarding.service;

import com.preonboarding.dto.RequestEmployRegistrationDto;
import com.preonboarding.exception.EmploySignUpFailException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class EmployServiceImplTest {

    @Autowired
    private EmployService employService;

    /**
     * 예외처리 test
     * 채용 공고 저장중 예외 발생 했을 때의 Exception 타입 확인
     */
    @Test
    void employInsert() {

        RequestEmployRegistrationDto dto = RequestEmployRegistrationDto.builder()
                .companyId(1L)
                .employPosition("백엔드 주니어 개발자")
                .employMoneyGift(1000000L)
                .employContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .build();

        Assertions.assertThatThrownBy(() -> employService.employInsert(dto)).isInstanceOf(EmploySignUpFailException.class);
    }
}