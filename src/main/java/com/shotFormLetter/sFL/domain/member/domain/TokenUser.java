package com.shotFormLetter.sFL.domain.member.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenUser {
    private String token;
    private String userName;
    private Long id;
}
