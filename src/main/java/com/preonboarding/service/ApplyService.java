package com.preonboarding.service;

import com.preonboarding.dto.RequestApplyDto;
import com.preonboarding.entity.Apply;
import com.preonboarding.entity.Employ;
import com.preonboarding.entity.User;
import com.preonboarding.exception.AlreadyApplyEmployException;
import com.preonboarding.exception.ApplyFailException;
import com.preonboarding.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final EmployService employService;
    private final UserService userService;

    public void applyInsert(RequestApplyDto dto) {

        User findUser = userService.findUser(dto.getUserId());
        Employ findEmploy = employService.findEmploy(dto.getEmployId());

        // 지원 중복 확인
        List<Apply> findApplies = applyRepository.findByUserAndEmploy(findUser, findEmploy);
        if (!findApplies.isEmpty()) {
            throw new AlreadyApplyEmployException("이미 지원한 채용공고 입니다.", HttpStatus.BAD_REQUEST);
        }

        Apply newApply = Apply.builder()
                .user(findUser)
                .employ(findEmploy)
                .build();

        try {
            applyRepository.save(newApply);
        } catch (Exception e) {
            log.error("[ApplyService.ApplyInsert] ex", e);
            throw new ApplyFailException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
