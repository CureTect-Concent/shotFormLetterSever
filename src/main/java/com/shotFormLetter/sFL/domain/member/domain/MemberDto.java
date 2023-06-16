package com.shotFormLetter.sFL.domain.member.domain;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
public class MemberDto {
    @NotNull(message = "아이디를 입력해주세요")
    @Size(min = 3, max = 100)
    private String userId;

    @NotNull(message = "회원 이름을 입력해주세요")
    @Size(min = 3, max = 100)
    private String userName;

    @NotNull(message = "비밀번호를 입력해주세요")
    @Size(min = 8, max = 300)
    private String password;
}
