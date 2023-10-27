package com.preonboarding.service;

import com.preonboarding.common.util.TokenProvider;
import com.preonboarding.dto.RequestLoginDto;
import com.preonboarding.entity.User;
import com.preonboarding.exception.NotFoundUserException;
import com.preonboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // PR TEST
    @Value("${jwt.secret}")
    private String secretKey;

    private Long expiredMs = 1000 * 60 * 60L;

    public User findUser(String userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundUserException("유저 ID를 확인해주세요.", HttpStatus.BAD_REQUEST)
        );
    }

    public String login(RequestLoginDto dto) {
        // 인증 과정
        findUser(dto.getUserId());
        return TokenProvider.createJwt(dto.getUserId(), secretKey, expiredMs);
    }
}
