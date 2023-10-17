package com.preonboarding.dto;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmployDetailsDto {

    private Long employId;
    private String companyName;
    private String companyCountry;
    private String companyArea;
    private String employPosition;
    private Long employMoneyGift;
    private String employSkill;
    private String employContent;
    private List<Long> otherEmployList;
}
