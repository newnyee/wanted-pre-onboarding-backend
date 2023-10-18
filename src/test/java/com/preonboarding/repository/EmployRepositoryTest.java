package com.preonboarding.repository;

import com.preonboarding.entity.Company;
import com.preonboarding.entity.Employ;
import com.preonboarding.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class EmployRepositoryTest {

    @Autowired
    EmployRepository employRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyService companyService;

    private Company company;

    private Employ employ;

    @BeforeEach
    void init() {

        // 테이블 초기화
        employRepository.deleteAll();
        companyRepository.deleteAll();

        // 샘플 데이터 insert
        // 회사 가입
        Company saveCompany = Company.builder()
                .companyName("원티드랩")
                .companyCountry("한국")
                .companyArea("서울")
                .build();
        this.company = companyRepository.save(saveCompany);

        // 채용공고 등록
        Employ saveEmploy = Employ.builder()
                .company(company)
                .employPosition("백엔드 주니어 개발자")
                .employContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .employMoneyGift(1000000L)
                .employSkill("Python")
                .build();
        this.employ = employRepository.save(saveEmploy);
    }

    @Test
    void save() {
        assertThat(employRepository.findById(employ.getEmployId()).get()).isEqualTo(employ);
    }

    @Test
    void updateEmploy() {

        Employ updateEmploy = Employ.builder()
                .company(company)
                .employId(employ.getEmployId())
                .employPosition("백엔드 주니어 개발자")
                .employContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .employMoneyGift(1500000L) //수정
                .employSkill("Python")
                .build();

        assertThat(employRepository.save(updateEmploy).getEmployMoneyGift()).isEqualTo(1500000L);
    }

    @Test
    void delete() {

        employRepository.deleteById(employ.getEmployId());
        assertThat(employRepository.findById(employ.getEmployId())).isEmpty();
    }

    @Test
    void findByEmployIdAndCompany() {

        Optional<Employ> getEmploy = employRepository.findByEmployIdAndCompany(employ.getEmployId(), company);
        Assertions.assertThat(getEmploy).isNotEmpty();
    }

    @Test
    void findByCompanyAndEmployIdNot() {

        List<Employ> getEmploy = employRepository.findByCompanyAndEmployIdNot(company, employ.getEmployId());
        Assertions.assertThat(getEmploy).isEmpty();
    }
}