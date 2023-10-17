package com.preonboarding.api;

import com.preonboarding.common.CommonResponseDto;
import com.preonboarding.dto.RequestApplyDto;
import com.preonboarding.service.ApplyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/apply")
public class ApplyController {

    private final ApplyService applyService;

    /**
     * 채용공고 지원 API
     *
     * @param requestApplyDto 지원할 공고 ID, 유저 ID
     * @return 지원 완료 상태코드 반환
     */
    @PostMapping
    public ResponseEntity<CommonResponseDto> apply(@Validated @RequestBody RequestApplyDto requestApplyDto) {
        applyService.applyInsert(requestApplyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponseDto(HttpStatus.CREATED));
    }
}
