package com.preonboarding.dto;

import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestEmployRegistrationDto {

    private Long companyId;

    private String employPosition;

    private Long employMoneyGift;

    private String employContent;

    private String employSkill;
}
