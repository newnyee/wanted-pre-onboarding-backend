package com.preonboarding.dto;

import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmployInfoDto {

    private Long employId;
    private String companyName;
    private String companyCountry;
    private String companyArea;
    private String employPosition;
    private Long employMoneyGift;
    private String employSkill;
}
