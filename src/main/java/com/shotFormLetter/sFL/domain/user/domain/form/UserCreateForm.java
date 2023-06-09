package com.shotFormLetter.sFL.domain.user.domain.form;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Getter
@Setter
public class UserCreateForm {
    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String username;

    @NotEmpty(message = "닉네임 확인은 필수항목입니다.")
    private String user_nickname;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password;

}
