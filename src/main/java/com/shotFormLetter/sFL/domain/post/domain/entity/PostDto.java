package com.shotFormLetter.sFL.domain.post.domain.entity;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
public class PostDto {
    @NotNull
    @Size(min = 3, max = 100)
    private String userName;

    @NotNull
    @Size(min =3, max=100)
    private String content;

}
