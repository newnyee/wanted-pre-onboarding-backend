package com.preonboarding.dto;

import lombok.*;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestLoginDto {

    private String userId;
    private String password;
}
