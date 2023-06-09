package com.shotFormLetter.sFL.domain.post.domain.service;

import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    Post createPost(String content);
    Post getPostById(int postId);
    List<Post> getAllPosts();
    void updatePost(Post post, String content);
    void deletePost(int postId);
}
