package com.preonboarding.service;
import com.preonboarding.dto.*;
import com.preonboarding.entity.Company;
import com.preonboarding.entity.Employ;
import com.preonboarding.exception.EmploySignUpFailException;
import com.preonboarding.exception.EmployUpdateFailException;
import com.preonboarding.exception.FailToDeleteEmployException;
import com.preonboarding.exception.NotFoundEmployException;
import com.preonboarding.repository.EmployRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmployService {

    private final EmployRepository employRepository;
    private final CompanyService companyService;

    public void employInsert(RequestEmployRegistrationDto dto) {

        Company findCompany = companyService.findById(dto.getCompanyId());

        Employ newEmploy = Employ.builder()
                .company(findCompany)
                .employSkill(dto.getEmploySkill())
                .employPosition(dto.getEmployPosition())
                .employMoneyGift(dto.getEmployMoneyGift())
                .employContent(dto.getEmployContent())
                .build();

        try {
            employRepository.save(newEmploy);
        } catch (Exception e) {
            log.error("[EmployService.employInsert] ex", e);
            throw new EmploySignUpFailException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseUpdateEmployDto employUpdate(RequestEmployUpdateDto dto) {

        Company findCompany = companyService.findById(dto.getCompanyId());
        findByEmployIdAndCompany(dto.getEmployId(), findCompany);

        Employ updateEmploy = Employ.builder()
                .company(findCompany)
                .employId(dto.getEmployId())
                .employPosition(dto.getEmployPosition())
                .employMoneyGift(dto.getEmployMoneyGift())
                .employContent(dto.getEmployContent())
                .employSkill(dto.getEmploySkill())
                .build();
        try {
            Employ resultUpdate = employRepository.save(updateEmploy);

            return ResponseUpdateEmployDto.builder()
                    .employPosition(resultUpdate.getEmployPosition())
                    .employMoneyGift(resultUpdate.getEmployMoneyGift())
                    .employContent(resultUpdate.getEmployContent())
                    .employSkill(resultUpdate.getEmploySkill())
                    .build();
        } catch (Exception e) {
            log.error("[EmployService.employUpdate] ex", e);
            throw new EmployUpdateFailException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void employDelete(RequestDeleteEmployDto dto) {

        Company findCompany = companyService.findById(dto.getCompanyId());
        findByEmployIdAndCompany(dto.getEmployId(), findCompany);

        try {
            employRepository.deleteById(dto.getEmployId());
        } catch (Exception e) {
            log.error("[EmployService.employInsert] ex", e);
            throw new FailToDeleteEmployException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<ResponseEmployInfoDto> findEmploys() {

        List<Employ> employList = employRepository.findAll();

        List<ResponseEmployInfoDto> list = new ArrayList<>();
        for (Employ employ : employList) {
            ResponseEmployInfoDto employInfo = ResponseEmployInfoDto.builder()
                    .employId(employ.getEmployId())
                    .companyName(employ.getCompany().getCompanyName())
                    .companyCountry(employ.getCompany().getCompanyCountry())
                    .companyArea(employ.getCompany().getCompanyArea())
                    .employPosition(employ.getEmployPosition())
                    .employMoneyGift(employ.getEmployMoneyGift())
                    .employSkill(employ.getEmploySkill())
                    .build();
            list.add(employInfo);
        }
        return list;
    }

    public ResponseEmployDetailsDto detailsEmploy(Long employId) {

        Employ findEmploy = findEmploy(employId);

        Company findCompany = companyService.findById(findEmploy.getCompany().getCompanyId());

        List<Employ> employs = employRepository.findByCompanyAndEmployIdNot(findCompany, employId);

        List<Long> employIds = new ArrayList<>();
        for (Employ employ : employs) {
            employIds.add(employ.getEmployId());
        }

        return ResponseEmployDetailsDto.builder()
                .employId(findEmploy.getEmployId())
                .companyName(findEmploy.getCompany().getCompanyName())
                .companyCountry(findEmploy.getCompany().getCompanyCountry())
                .companyArea(findEmploy.getCompany().getCompanyArea())
                .employPosition(findEmploy.getEmployPosition())
                .employMoneyGift(findEmploy.getEmployMoneyGift())
                .employSkill(findEmploy.getEmploySkill())
                .employContent(findEmploy.getEmployContent())
                .otherEmployList(employIds)
                .build();
    }

    public Employ findEmploy(Long employId) {
        return employRepository.findById(employId).orElseThrow(
                () -> new NotFoundEmployException("채용공고 ID를 확인해주세요.", HttpStatus.BAD_REQUEST)
        );
    }

    public Employ findByEmployIdAndCompany(Long id, Company company) {
        return employRepository.findByEmployIdAndCompany(id, company).orElseThrow(
                () -> new NotFoundEmployException("채용공고 ID를 확인해주세요.", HttpStatus.BAD_REQUEST)
        );
    }
}
