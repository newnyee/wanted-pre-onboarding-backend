package com.preonboarding.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDeleteEmployDto {

    @NotNull
    @Range(min = 0)
    private Long companyId;

    @NotNull
    @Range(min = 0)
    private Long employId;
}
