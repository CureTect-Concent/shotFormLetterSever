package com.shotFormLetter.sFL.domain.post.domain.service;

import com.shotFormLetter.sFL.domain.member.domain.Member;
import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;


    @Override
    public Post createPost(String content, Member userName) {
        Post post = new Post();
        post.setContent(content);
        post.setUserName(userName);
        post.setCreatedAt(LocalDateTime.now());
        this.postRepository.save(post);
        return post;
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow();
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void updatePost(Post post, String content) {
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        this.postRepository.save(post);
    }




    @Override
    public void deletePost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new NoSuchElementException("Post not found");
        }
        postRepository.deleteById(postId);
    }
}
