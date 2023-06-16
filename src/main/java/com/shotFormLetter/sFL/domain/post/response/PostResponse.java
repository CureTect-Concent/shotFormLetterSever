package com.shotFormLetter.sFL.domain.post.response;

import lombok.Setter;

@Setter
public class PostResponse {
    private String message;

    public static PostResponse createApiResponse(String message) {
        PostResponse response = new PostResponse();
        response.setMessage(message);
        return response;
    }
}
