package com.preonboarding.entity;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@ToString
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employId;

    @NotNull
    private Long companyId;

    @NotNull
    private String employPosition;

    @NotNull
    private Long employMoneyGift;

    @NotNull
    private String employContent;

    @NotNull
    private String employSkill;
}
