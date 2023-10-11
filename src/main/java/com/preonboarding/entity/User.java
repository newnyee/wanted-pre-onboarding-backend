package com.preonboarding.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    private String userId;

    @NotNull
    private String userName;

    @NotNull
    private String userArea;

    @NotNull
    private String userSkill;

    @NotNull
    private String userPosition;

    @OneToMany(mappedBy = "user")
    private List<Apply> applies = new ArrayList<>();
}
