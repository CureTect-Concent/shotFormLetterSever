package com.shotFormLetter.sFL.domain.post.dto;

public class PostDto {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void updateContent(String content){
        this.content=content;
    }
}
