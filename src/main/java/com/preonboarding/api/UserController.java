package com.preonboarding.api;

import com.preonboarding.common.CommonResponseDto;
import com.preonboarding.dto.RequestLoginDto;
import com.preonboarding.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")

    public ResponseEntity<CommonResponseDto> login(@RequestBody RequestLoginDto requestLoginDto) {
        return ResponseEntity.ok().body(new CommonResponseDto(HttpStatus.OK, userService.login(requestLoginDto)));
    }
}
