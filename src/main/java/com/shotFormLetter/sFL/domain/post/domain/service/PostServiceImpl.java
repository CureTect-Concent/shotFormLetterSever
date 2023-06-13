package com.shotFormLetter.sFL.domain.post.domain.service;

import com.shotFormLetter.sFL.domain.member.domain.Member;
import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import com.shotFormLetter.sFL.domain.post.dto.PostDto;
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
    public Post createPost(PostDto postDto, Member userName) {
        String content = postDto.getContent();
        // 2. 게시글 생성 시간을 현재 시간으로 설정합니다.
        LocalDateTime createdAt = LocalDateTime.now();
        // 3. 새로운 게시글 객체를 생성하고 필드 값을 설정합니다.
        Post post = new Post();
        post.setContent(content);
        post.setUserName(userName); // 사용자 아이디를 가져와 설정합니다.
        post.setCreatedAt(createdAt);
        // (기타 필요한 필드 설정)
        // 4. 게시글을 저장하고 생성된 게시글을 반환합니다.
        return postRepository.save(post);
    }

    @Override
    public List<Post> PostByUserName(Member userName) {
        return postRepository.getPostByUserName(userName);
    }

    @Override
    public Post getPostByPostId(Long postId){
        return postRepository.getPostByPostId(postId);
    }

    @Override
    public Post updatePost(Post post,PostDto postDto) {
        String content = postDto.getContent();
        post.setCreatedAt(LocalDateTime.now());
        post.setContent(content);
        return this.postRepository.save(post);
    }




    @Override
    public void deletePost(Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new NoSuchElementException("Post not found");
        }
        postRepository.deleteById(postId);
    }
}
