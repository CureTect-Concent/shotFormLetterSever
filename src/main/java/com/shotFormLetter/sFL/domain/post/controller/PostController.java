package com.shotFormLetter.sFL.domain.post.controller;

import com.shotFormLetter.sFL.ExceptionHandler.NotFoundException;
import com.shotFormLetter.sFL.domain.member.domain.Member;
import com.shotFormLetter.sFL.domain.member.domain.MemberRepository;
import com.shotFormLetter.sFL.domain.member.token.JwtTokenProvider;
import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import com.shotFormLetter.sFL.domain.post.domain.service.PostService;

import com.shotFormLetter.sFL.domain.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PostRepository postRepository;
//    @GetMapping()
//    public ResponseEntity<List<Post>> getAllPosts() {
//        List<Post> posts = postService.getAllPosts();
//        return ResponseEntity.ok(posts);
//    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody @Valid PostDto postDto, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);

        String userId = jwtTokenProvider.getUserPk(token);

        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        if (!optionalMember.isPresent()) {
            throw new IllegalStateException("User not found");
        }
        Member member = optionalMember.get();
        Post createdPost = postService.createPost(postDto, member);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable("userId") @Valid String userId, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);

        String nowId = jwtTokenProvider.getUserPk(token);

        Optional<Member> optionalMember = memberRepository.findByUserId(nowId);
        if (!optionalMember.isPresent()) {
            throw new IllegalStateException("User not found");
        }
        Member member = optionalMember.get();
        List<Post> posts = postService.PostByUserName(member);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/find/{userId}/{postId}")
    public ResponseEntity<Post> getPostsByUserId(@PathVariable("userId") @Valid String userId,
                                                 @PathVariable("postId")@Valid Long postId,
                                                 HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);

        String nowId = jwtTokenProvider.getUserPk(token);

        Optional<Member> optionalMember = memberRepository.findByUserId(nowId);
        if (!optionalMember.isPresent()) {
            throw new IllegalStateException("User not found");
        }
        Member member = optionalMember.get();
        Post post = postService.getPostByPostId(postId);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/modify/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId,
                                           @RequestBody @Valid PostDto postDto,
                                           HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String userId = jwtTokenProvider.getUserPk(token);

        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        if (!optionalMember.isPresent()) {
            throw new IllegalStateException("User not found");
        }

        Member member = optionalMember.get();
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (!optionalPost.isPresent()) {
            throw new IllegalArgumentException("Post not found");
        }
        Post post = optionalPost.get();
        // 작성자 ID와 토큰의 ID 값이 일치하는지 확인
        if (!post.getUserName().getId().equals(member.getId())) {
            throw new IllegalArgumentException("You do not have permission to update this post");
        }

        Post updatedPost=postService.updatePost(post,postDto);
        return ResponseEntity.ok(updatedPost);
    }

//    @DeleteMapping("/delete/{postId}")
//    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
//        Post post = postService.getPostById(postId);
//        if (post == null) {
//            return ResponseEntity.notFound().build();
//        }
//        postService.deletePost(postId);
//        return ResponseEntity.noContent().build();
//    }
}
