package com.preonboarding.service;

import com.preonboarding.dto.RequestApplyDto;
import com.preonboarding.entity.Company;
import com.preonboarding.entity.Employ;
import com.preonboarding.entity.User;
import com.preonboarding.exception.AlreadyApplyEmployException;
import com.preonboarding.repository.ApplyRepository;
import com.preonboarding.repository.CompanyRepository;
import com.preonboarding.repository.EmployRepository;
import com.preonboarding.repository.UserRepository;
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
class ApplyServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployRepository employRepository;

    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private ApplyService applyService;

    private Company company;
    private User user;
    private Employ employ;

    @BeforeEach
    void init() {

        // 테이블 초기화
        userRepository.deleteAll();
        companyRepository.deleteAll();
        employRepository.deleteAll();
        applyRepository.deleteAll();

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

        // 유저 가입
        User saveUser = User.builder()
                .userId("user1")
                .userName("홍길동")
                .userArea("서울")
                .userSkill("Python")
                .userPosition("백엔드 주니어 개발자")
                .build();

        this.user = userRepository.save(saveUser);
    }

    @Test
    void applyInsert() {

        // 정상 지원
        RequestApplyDto dto1 = RequestApplyDto.builder()
                .userId(user.getUserId())
                .employId(employ.getEmployId())
                .build();
        applyService.applyInsert(dto1);

        assertThat(applyRepository.findAll().size()).isEqualTo(1);

        // 중복 지원
        RequestApplyDto dto2 = RequestApplyDto.builder()
                .userId(user.getUserId())
                .employId(employ.getEmployId())
                .build();

        assertThatThrownBy(() -> applyService.applyInsert(dto2)).isInstanceOf(AlreadyApplyEmployException.class);

    }
}