package com.preonboarding.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Recruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long recruitId;

    @NotNull
    private long companyId;

    @NotNull
    private String recruitPosition;

    @NotNull
    private long recruitMoneyGift;

    @NotNull
    private String recruitContent;

    @NotNull
    private String recruitSkill;
}
