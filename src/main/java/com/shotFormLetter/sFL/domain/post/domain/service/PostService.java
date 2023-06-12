package com.shotFormLetter.sFL.domain.post.domain.service;

import com.shotFormLetter.sFL.domain.member.domain.Member;
import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.domain.entity.PostDto;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    Post getPostById(Member userName);
    List<Post> getAllPosts();
    void updatePost(Post post, String content);
    void deletePost(Long postId);
    Post createPost(PostDto postDto, String userIdAsString);
}
