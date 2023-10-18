package com.preonboarding.service;

import com.preonboarding.dto.RequestDeleteEmployDto;
import com.preonboarding.dto.RequestEmployRegistrationDto;
import com.preonboarding.dto.RequestEmployUpdateDto;
import com.preonboarding.entity.Company;
import com.preonboarding.entity.Employ;
import com.preonboarding.exception.EmploySignUpFailException;
import com.preonboarding.exception.NotFoundCompanyException;
import com.preonboarding.exception.NotFoundEmployException;
import com.preonboarding.repository.CompanyRepository;
import com.preonboarding.repository.EmployRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class EmployServiceTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployRepository employRepository;

    @Autowired
    private EmployService employService;

    private Company company;
    private Employ employ;

    @BeforeEach
    void init() {

        // 테이블 초기화
        companyRepository.deleteAll();
        employRepository.deleteAll();

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

    /**
     * 예외처리 test
     * 채용 공고 저장중 예외 발생 했을 때의 Exception 타입 확인
     */
    @Test
    void employInsert() {

        // 정상 로직
        RequestEmployRegistrationDto dto1 = RequestEmployRegistrationDto.builder()
                .companyId(company.getCompanyId())
                .employPosition("프론트엔드 주니어 개발자")
                .employMoneyGift(2000000L)
                .employContent("원티드랩에서 프론트엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .employSkill("Node.js")
                .build();
        employService.employInsert(dto1);
        assertThat(employRepository.findAll().size()).isEqualTo(2);

        // dto 멤버 변수중 하나의 변수를 넣지 않았을 경우 (employSkill)
        RequestEmployRegistrationDto dto2 = RequestEmployRegistrationDto.builder()
                .companyId(company.getCompanyId())
                .employPosition("백엔드 주니어 개발자")
                .employMoneyGift(1000000L)
                .employContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .build();
        assertThatThrownBy(() -> employService.employInsert(dto2)).isInstanceOf(EmploySignUpFailException.class);
    }

    @Test
    void employUpdate() {

        RequestEmployUpdateDto dto1 = RequestEmployUpdateDto.builder()
                .companyId(company.getCompanyId())
                .employId(employ.getEmployId())
                .employPosition("백엔드 주니어 개발자")
                .employContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .employMoneyGift(1500000L) //1000000 -> 1500000 수정
                .employSkill("Python")
                .build();

        assertThat(employService.employUpdate(dto1).getEmployMoneyGift()).isEqualTo(1500000L);
    }

    @Test
    void employDelete() {

        // 채용공고 아이디가 존재하지 않는 아이디인 경우
        RequestDeleteEmployDto dto1 = RequestDeleteEmployDto.builder()
                .companyId(company.getCompanyId())
                .employId(employ.getEmployId() + 1) //임의의 수 입력
                .build();
        assertThatThrownBy(() -> employService.employDelete(dto1)).isInstanceOf(NotFoundEmployException.class);

        // 회사 아이디가 존재하지 않는 아이디인 경우
        RequestDeleteEmployDto dto2 = RequestDeleteEmployDto.builder()
                .companyId(company.getCompanyId() + 1) //임의의 수 입력
                .employId(employ.getEmployId())
                .build();
        assertThatThrownBy(() -> employService.employDelete(dto2)).isInstanceOf(NotFoundCompanyException.class);

        // 정상 로직
        RequestDeleteEmployDto dto3 = RequestDeleteEmployDto.builder()
                .companyId(company.getCompanyId())
                .employId(employ.getEmployId())
                .build();
        employService.employDelete(dto3);
        assertThat(employRepository.findById(employ.getEmployId())).isEmpty();
    }

    @Test
    void findEmploys() {
        assertThat(employService.findEmploys().size()).isEqualTo(1);
    }

    @Test
    void detailsEmploy() {
        assertThat(employService.detailsEmploy(employ.getEmployId()).getCompanyName()).isEqualTo("원티드랩");
    }

    @Test
    void findEmploy() {

        // 정확한 아이디 입력
        assertThat(employService.findEmploy(employ.getEmployId())).isEqualTo(employ);

        // 잘못된 아이디 입력
        assertThatThrownBy(() -> employService.findEmploy(employ.getEmployId() + 1)).isInstanceOf(NotFoundEmployException.class);
    }

    @Test
    void findByEmployIdAndCompany() {

        // 정확한 아이디 입력
        assertThat(employRepository.findByEmployIdAndCompany(employ.getEmployId(), company)).isNotEmpty();

        // 잘못된 아이디 입력
        assertThatThrownBy(() -> employService.findByEmployIdAndCompany(employ.getEmployId() + 1, company)).isInstanceOf(NotFoundEmployException.class);
    }
}