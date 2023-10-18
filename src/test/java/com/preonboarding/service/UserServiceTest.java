package com.preonboarding.service;

import com.preonboarding.entity.Company;
import com.preonboarding.entity.Employ;
import com.preonboarding.entity.User;
import com.preonboarding.exception.NotFoundUserException;
import com.preonboarding.repository.ApplyRepository;
import com.preonboarding.repository.CompanyRepository;
import com.preonboarding.repository.EmployRepository;
import com.preonboarding.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    void init() {

        // 테이블 초기화
        userRepository.deleteAll();

        // 샘플 데이터 insert
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
    void findUser() {

        // 정확한 아이디 입력
        assertThat(userService.findUser(user.getUserId())).isEqualTo(user);

        // 잘못된 아이디 입력
        assertThatThrownBy(() -> userService.findUser(user.getUserId() + 1)).isInstanceOf(NotFoundUserException.class);
    }
}