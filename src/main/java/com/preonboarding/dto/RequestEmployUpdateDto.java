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
public class RequestEmployUpdateDto {

    @NotNull
    @Range(min = 0)
    private Long companyId;

    @NotNull
    @Range(min = 0)
    private Long employId;

    @NotBlank
    private String employPosition;

    @NotNull
    @Range(min = 0)
    private Long employMoneyGift;

    @NotBlank
    private String employContent;

    @NotBlank
    private String employSkill;
}
