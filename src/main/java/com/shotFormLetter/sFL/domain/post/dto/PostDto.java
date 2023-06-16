package com.shotFormLetter.sFL.domain.post.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class PostDto {
    @NotNull(message = "컨텐츠 없음")
    private String content;

    @NotNull(message = "제목 없음")
    private String title;

    @NotNull(message="미디어 타입 없음")
    private List<String> media_reference;

}
