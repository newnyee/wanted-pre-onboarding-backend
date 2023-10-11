package com.preonboarding.entity;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

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
    private String employPosition;

    @NotNull
    private Long employMoneyGift;

    @NotNull
    private String employContent;

    @NotNull
    private String employSkill;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "employ")
    private List<Apply> applies = new ArrayList<>();
}
