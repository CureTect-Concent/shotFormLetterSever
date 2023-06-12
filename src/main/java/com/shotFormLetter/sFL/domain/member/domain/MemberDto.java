package com.shotFormLetter.sFL.domain.member.domain;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
public class MemberDto {
    @NotNull
    @Size(min = 3, max = 100)
    private String userId;

    @NotNull
    @Size(min = 3, max = 100)
    private String userName;

    @NotNull
    @Size(min = 8, max = 300)
    private String password;
}
