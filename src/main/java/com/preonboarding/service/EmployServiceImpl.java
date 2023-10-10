package com.preonboarding.service;
import com.preonboarding.dto.RequestEmployRegistrationDto;
import com.preonboarding.dto.ResponseSavedEmployDto;
import com.preonboarding.entity.Employ;
import com.preonboarding.repository.EmployRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployServiceImpl implements EmployService {

    private final EmployRepository employRepository;

    @Override
    public ResponseSavedEmployDto employInsert(RequestEmployRegistrationDto dto) {

        Employ newEmploy = Employ.builder()
                .companyId(dto.getCompanyId())
                .employSkill(dto.getEmploySkill())
                .employPosition(dto.getEmployPosition())
                .employMoneyGift(dto.getEmployMoneyGift())
                .employContent(dto.getEmployContent())
                .build();

        Employ savedEmploy = employRepository.save(newEmploy);

        return ResponseSavedEmployDto.builder()
                .employId(newEmploy.getEmployId())
                .companyId(newEmploy.getCompanyId())
                .employSkill(newEmploy.getEmploySkill())
                .employPosition(newEmploy.getEmployPosition())
                .employMoneyGift(newEmploy.getEmployMoneyGift())
                .employContent(newEmploy.getEmployContent())
                .build();
    }
}
