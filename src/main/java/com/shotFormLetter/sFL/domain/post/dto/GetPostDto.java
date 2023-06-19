package com.shotFormLetter.sFL.domain.post.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class GetPostDto {
    private Long postId;
    private String content;
    private String title;
    private Long userPkId;
    private String userId;
    private List<String> s3Urls;
    private List<String> media_reference;
    private LocalDateTime createdAt;
}
