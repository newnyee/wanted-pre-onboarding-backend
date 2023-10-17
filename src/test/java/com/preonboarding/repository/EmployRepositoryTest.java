package com.preonboarding.repository;

import com.preonboarding.dto.RequestEmployUpdateDto;
import com.preonboarding.entity.Company;
import com.preonboarding.entity.Employ;
import com.preonboarding.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class EmployRepositoryTest {

    @Autowired
    EmployRepository employRepository;

    @Autowired
    CompanyService companyService;

    @Test
    void save() {

        Company findCompany = companyService.findById(1L);

        Employ employ = Employ.builder()
                .company(findCompany)
                .employPosition("백엔드 주니어 개발자")
                .employContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .employMoneyGift(1000000L)
                .employSkill("Python")
                .build();

        Employ savedEmploy = employRepository.save(employ);
        Employ findEmploy = employRepository.findById(employ.getEmployId()).get();

        assertThat(findEmploy).isEqualTo(savedEmploy);
    }

    @Test
    void updateEmploy() {

        Company findCompany = companyService.findById(1L);

        Employ employ = Employ.builder()
                .company(findCompany)
                .employPosition("백엔드 주니어 개발자")
                .employContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .employMoneyGift(1000000L)
                .employSkill("Python")
                .build();

        Long saveEmployId = employRepository.save(employ).getEmployId();

        Employ updateEmploy = Employ.builder()
                .company(findCompany)
                .employId(saveEmployId)
                .employPosition("백엔드 주니어 개발자")
                .employContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .employMoneyGift(1500000L)
                .employSkill("Python")
                .build();

        Employ save = employRepository.save(updateEmploy);

        assertThat(save.getEmployMoneyGift()).isEqualTo(1500000L);
    }

    @Test
    void delete() {

        Company findCompany = companyService.findById(1L);

        Employ employ = Employ.builder()
                .company(findCompany)
                .employPosition("백엔드 주니어 개발자")
                .employContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .employMoneyGift(1000000L)
                .employSkill("Python")
                .build();
        Employ savedEmploy = employRepository.save(employ);
        Long employId = savedEmploy.getEmployId();

        employRepository.deleteById(employId);
        Optional<Employ> findemploy = employRepository.findById(employId);

        assertThat(findemploy).isEmpty();
    }
}