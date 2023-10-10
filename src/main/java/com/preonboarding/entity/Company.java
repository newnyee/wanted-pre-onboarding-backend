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
    private Long companyId;

    @NotNull
    @Column(unique = true)
    private String companyName;

    @NotNull
    private String companyCountry;

    @NotNull
    private String companyArea;
}
