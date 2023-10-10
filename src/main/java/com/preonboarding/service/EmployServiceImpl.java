package com.preonboarding.service;
import com.preonboarding.dto.RequestEmployRegistrationDto;
import com.preonboarding.dto.RequestEmployUpdateDto;
import com.preonboarding.dto.ResponseSavedEmployDto;
import com.preonboarding.dto.ResponseUpdateEmployDto;
import com.preonboarding.entity.Employ;
import com.preonboarding.repository.EmployRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
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

    @Override
    public ResponseUpdateEmployDto employUpdate(RequestEmployUpdateDto requestEmployUpdateDto) {

        Employ updateEmploy = Employ.builder()
                .employId(requestEmployUpdateDto.getEmployId())
                .companyId(requestEmployUpdateDto.getCompanyId())
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
}
