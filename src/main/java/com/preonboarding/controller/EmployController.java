package com.preonboarding.controller;
import com.preonboarding.dto.RequestEmployUpdateDto;
import com.preonboarding.dto.RequestEmployRegistrationDto;
import com.preonboarding.dto.ResponseSavedEmployDto;
import com.preonboarding.dto.ResponseUpdateEmployDto;
import com.preonboarding.service.EmployService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     * @return 등록된 채용공고 정보
     */
    @PostMapping
    public ResponseEntity signUp(@RequestBody RequestEmployRegistrationDto requestEmployRegistrationDto) {
        ResponseSavedEmployDto responseSavedEmployDto = employService.employInsert(requestEmployRegistrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseSavedEmployDto);
    }


    /**
     * 채용공고 수정 API
     *
     * @param requestEmployUpdateDto 수정할 채용공고 정보
     * @return 수정된 채용 공고 정보
     */
    @PutMapping
    public ResponseEntity update(@RequestBody RequestEmployUpdateDto requestEmployUpdateDto) {
        ResponseUpdateEmployDto updatedEmploy = employService.employUpdate(requestEmployUpdateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedEmploy);
    }

    /**
     * 채용공고 삭제 API
     * @param employId 삭제할 채용공고 ID
     * @return 삭제 완료 상태코드 반환
     */
    @DeleteMapping("/{employId}")
    public ResponseEntity delete(@PathVariable Long employId) {
        employService.delete(employId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
