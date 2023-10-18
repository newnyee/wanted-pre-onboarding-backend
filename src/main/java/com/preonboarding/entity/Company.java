package com.preonboarding.entity;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@ToString
@Entity
@Getter
@Builder
@AllArgsConstructor
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

    @OneToMany(mappedBy = "company")
    private List<Employ> employs = new ArrayList<>();
}
