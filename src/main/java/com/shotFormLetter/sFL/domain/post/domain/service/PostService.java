package com.shotFormLetter.sFL.domain.post.domain.service;

import com.shotFormLetter.sFL.domain.member.domain.Member;
import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import com.shotFormLetter.sFL.domain.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {
    Post createPost(PostDto postDto, Member userName);
    Post getPostById(Long postId);
    List<Post> getAllPosts();
    Post updatePost(Post post, PostDto postDto);
    void deletePost(Long postId);
}
