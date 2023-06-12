package com.shotFormLetter.sFL.domain.post.domain.service;

import com.shotFormLetter.sFL.domain.member.domain.Member;
import com.shotFormLetter.sFL.domain.member.domain.MemberDto;
import com.shotFormLetter.sFL.domain.member.domain.MemberRepository;
import com.shotFormLetter.sFL.domain.member.token.JwtTokenProvider;
import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.domain.entity.PostDto;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

//    @Override
//    public Post createPost(String content, Member userName) {
//        Post post = new Post();
//        post.setContent(content);
//        post.setUserName(userName);
//        post.setCreatedAt(LocalDateTime.now());
//        this.postRepository.save(post);
//        return post;
//    }



//        Member member = Member.builder()
//                .userId(memberDto.getUserId())
//                .userName(memberDto.getUserName())
//                .password(passwordEncoder.encode(memberDto.getPassword()))  //비밀번호 인코딩
//                .roles(Collections.singletonList("ROLE_USER"))         //roles는 최초 USER로 설정
//                .build();
//
//        return memberRepository.save(member).getId();


    @Override
    public Post createPost(PostDto postDto, String userIdAsString) {
        // 1. PostDto에서 게시글 내용과 작성자 정보를 가져옵니다.
        String content = postDto.getContent();

        // 2. 게시글 생성 시간을 현재 시간으로 설정합니다.
        LocalDateTime createdAt = LocalDateTime.now();

        // 3. 새로운 게시글 객체를 생성하고 필드 값을 설정합니다.
        Post post = new Post();
        post.setContent(content);
        post.setUserName();
        post.setCreatedAt(createdAt);
        // (기타 필요한 필드 설정)

        // 4. 게시글을 저장하고 생성된 게시글을 반환합니다.
        return postRepository.save(post);
    }

    @Override
    public Post getPostById(Member userName) {
        return postRepository.findById(userName.getId())
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
