package com.preonboarding.service;
import com.preonboarding.dto.*;
import com.preonboarding.entity.Employ;
import com.preonboarding.repository.EmployRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployServiceImpl implements EmployService {

    private final EmployRepository employRepository;

    @Override
    public ResponseSavedEmployDto employInsert(RequestEmployRegistrationDto dto) {

        Employ newEmploy = Employ.builder()
                .employSkill(dto.getEmploySkill())
                .employPosition(dto.getEmployPosition())
                .employMoneyGift(dto.getEmployMoneyGift())
                .employContent(dto.getEmployContent())
                .build();

        Employ savedEmploy = employRepository.save(newEmploy);

        return ResponseSavedEmployDto.builder()
                .employId(newEmploy.getEmployId())
                .employSkill(newEmploy.getEmploySkill())
                .employPosition(newEmploy.getEmployPosition())
                .employMoneyGift(newEmploy.getEmployMoneyGift())
                .employContent(newEmploy.getEmployContent())
                .build();
    }

    @Override
    public ResponseUpdateEmployDto employUpdate(RequestEmployUpdateDto requestEmployUpdateDto) {

        Employ updateEmploy = Employ.builder()
                .employId(requestEmployUpdateDto.getEmployId())
                .employPosition(requestEmployUpdateDto.getEmployPosition())
                .employMoneyGift(requestEmployUpdateDto.getEmployMoneyGift())
                .employContent(requestEmployUpdateDto.getEmployContent())
                .employSkill(requestEmployUpdateDto.getEmploySkill())
                .build();

        Employ resultUpdate = employRepository.save(updateEmploy);

        return ResponseUpdateEmployDto.builder()
                .employPosition(resultUpdate.getEmployPosition())
                .employMoneyGift(resultUpdate.getEmployMoneyGift())
                .employContent(resultUpdate.getEmployContent())
                .employSkill(resultUpdate.getEmploySkill())
                .build();
    }

    @Override
    public void delete(Long employId) {
        employRepository.deleteById(employId);
    }

    @Override
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

    @Transactional
    @Override
    public ResponseEmployDetailsDto detailsEmploy(Long employId) {

        Employ employ = employRepository.findById(employId).get();
        return ResponseEmployDetailsDto.builder()
                .employId(employ.getEmployId())
                .companyName(employ.getCompany().getCompanyName())
                .companyCountry(employ.getCompany().getCompanyCountry())
                .companyArea(employ.getCompany().getCompanyArea())
                .employPosition(employ.getEmployPosition())
                .employMoneyGift(employ.getEmployMoneyGift())
                .employSkill(employ.getEmploySkill())
                .employContent(employ.getEmployContent())
                .build();
    }
}
