package com.preonboarding.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestApplyDto {

    @NotNull
    @Range(min = 0)
    private Long employId;

    @NotBlank
    private String userId;
}
