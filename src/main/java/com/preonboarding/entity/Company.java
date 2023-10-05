package com.preonboarding.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long companyId;

    @NotNull
    private String companyName;

    @NotNull
    private String companyCountry;

    @NotNull
    private String companyArea;
}
