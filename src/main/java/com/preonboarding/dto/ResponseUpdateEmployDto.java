package com.preonboarding.dto;

import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUpdateEmployDto {
    private String employPosition;

    private Long employMoneyGift;

    private String employContent;

    private String employSkill;
}
