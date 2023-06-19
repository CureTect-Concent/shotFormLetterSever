package com.shotFormLetter.sFL.domain.post.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ThumbnailPostDto {
    private Long postId;
    private String title;
    private LocalDateTime createdAt;
    private String userId;
}
