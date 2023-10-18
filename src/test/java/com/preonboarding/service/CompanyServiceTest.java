package com.preonboarding.service;

import com.preonboarding.entity.Company;
import com.preonboarding.exception.NotFoundCompanyException;
import com.preonboarding.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@SpringBootTest
@Transactional
class CompanyServiceTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    private Company company;

    @BeforeEach
    void init() {

        // 테이블 초기화
        companyRepository.deleteAll();

        // 샘플 데이터 insert
        // 회사 가입
        Company saveCompany = Company.builder()
                .companyName("원티드랩")
                .companyCountry("한국")
                .companyArea("서울")
                .build();
        this.company = companyRepository.save(saveCompany);
    }

    @Test
    void findById() {

        // 정확한 아이디 입력
        assertThat(companyService.findById(company.getCompanyId())).isEqualTo(company);

        // 잘못된 아이디 입력
        assertThatThrownBy(() -> companyService.findById(company.getCompanyId() + 1)).isInstanceOf(NotFoundCompanyException.class);
    }
}