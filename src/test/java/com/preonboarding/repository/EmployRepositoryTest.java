package com.preonboarding.repository;

import com.preonboarding.dto.RequestEmployUpdateDto;
import com.preonboarding.entity.Employ;
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

    @Test
    void save() {

        Employ employ = Employ.builder()
                .companyId(1L)
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

        Employ employ = Employ.builder()
                .companyId(1L)
                .employPosition("백엔드 주니어 개발자")
                .employContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .employMoneyGift(1000000L)
                .employSkill("Python")
                .build();
        Long findEmploy = employRepository.save(employ).getEmployId();

        RequestEmployUpdateDto updateDto = RequestEmployUpdateDto.builder()
                .employId(findEmploy)
                .employPosition("백엔드 주니어 개발자")
                .employContent("원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..")
                .employMoneyGift(1500000L) // 변경값
                .employSkill("Python")
                .build();

        Employ updateEmploy = Employ.builder()
                .employId(updateDto.getEmployId())
                .employPosition(updateDto.getEmployPosition())
                .employContent(updateDto.getEmployContent())
                .employMoneyGift(updateDto.getEmployMoneyGift())
                .employSkill(updateDto.getEmploySkill())
                .build();

        Employ save = employRepository.save(updateEmploy);

        assertThat(save.getEmployMoneyGift()).isEqualTo(1500000L);
    }

    @Test
    void delete() {

        Employ employ = Employ.builder()
                .companyId(1L)
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