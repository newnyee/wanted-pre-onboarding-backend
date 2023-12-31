package com.preonboarding.api;
import com.preonboarding.common.CommonResponseDto;
import com.preonboarding.dto.*;
import com.preonboarding.service.EmployService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/employ")
public class EmployController {

    private final EmployService employService;

    /**
     * 채용공고 등록 API
     *
     * @param requestEmployRegistrationDto 등록할 채용공고 정보
     * @return 등록 완료 상태코드 반환
     */
    @PostMapping
    public ResponseEntity<CommonResponseDto> signUp(@Validated @RequestBody RequestEmployRegistrationDto requestEmployRegistrationDto) {
        log.debug("requestEmployRegistrationDto = {}", requestEmployRegistrationDto);
        employService.employInsert(requestEmployRegistrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponseDto(HttpStatus.CREATED));
    }

    /**
     * 채용공고 수정 API
     *
     * @param requestEmployUpdateDto 수정할 채용공고 정보
     * @return 수정된 채용 공고 정보
     */
    @PutMapping
    public ResponseEntity<CommonResponseDto> update(@Validated @RequestBody RequestEmployUpdateDto requestEmployUpdateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CommonResponseDto(HttpStatus.CREATED, employService.employUpdate(requestEmployUpdateDto))
        );
    }

    /**
     * 채용공고 삭제 API
     *
     * @param requestDeleteEmployDto 삭제할 채용공고 정보
     * @return 삭제 완료 상태코드 반환
     */
    @DeleteMapping
    public ResponseEntity delete(@Validated @RequestBody RequestDeleteEmployDto requestDeleteEmployDto) {
        employService.employDelete(requestDeleteEmployDto);
        return ResponseEntity.noContent().build();
    }

    /**
     * 채용공고 리스트 조회 API
     *
     * @return 채용공고 리스트 정보 반환
     */
    @GetMapping
    public ResponseEntity<CommonResponseDto> findEmployList() {
        return ResponseEntity.ok(new CommonResponseDto(HttpStatus.OK, employService.findEmploys()));
    }

    /**
     * 채용공고 상세 조회 API
     *
     * @param employId 상세 조회할 채용공고 id
     * @return 조회된 채용공고 정보
     */
    @GetMapping("/{employId}")
    public ResponseEntity<CommonResponseDto> detailsEmploy(@PathVariable Long employId) {
        return ResponseEntity.ok(new CommonResponseDto(HttpStatus.OK, employService.detailsEmploy(employId)));
    }
}
