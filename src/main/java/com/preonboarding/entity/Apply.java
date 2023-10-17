package com.preonboarding.entity;

import lombok.*;

import jakarta.persistence.*;


@ToString
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyId;

    @ManyToOne
    @JoinColumn(name = "employ_id")
    Employ employ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
