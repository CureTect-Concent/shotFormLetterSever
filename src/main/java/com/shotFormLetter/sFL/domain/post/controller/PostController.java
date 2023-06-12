package com.shotFormLetter.sFL.domain.post.controller;

import com.shotFormLetter.sFL.domain.member.domain.Member;
import com.shotFormLetter.sFL.domain.member.domain.MemberRepository;
import com.shotFormLetter.sFL.domain.member.token.JwtTokenProvider;
import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.domain.entity.PostDto;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import com.shotFormLetter.sFL.domain.post.domain.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @GetMapping()
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody @Valid PostDto postDto, HttpServletRequest request) {
        // 현재 인증된 사용자의 토큰을 가져옴
        String token = jwtTokenProvider.resolveToken(request);

        // 토큰으로부터 사용자 ID 추출
        String userId = jwtTokenProvider.getUserPk(token);

        // 사용자 ID로 사용자 정보 조회
        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        if (!optionalMember.isPresent()) {
            throw new IllegalStateException("User not found");
        }
        Member member = optionalMember.get();
        String NowUserId = member.getUserId(); // userId 필드를 추출
        String userIdAsString = String.valueOf(NowUserId); // String으로 변환
        // 게시글 생성
        Post createdPost = postService.createPost(postDto, userIdAsString);

        return ResponseEntity.ok(createdPost);
    }
//    @PostMapping("/create")
//    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String token, @RequestBody @Valid PostDto postDto) {
//        Post createdPost = postService.createPost(postDto, token);
//        return ResponseEntity.ok(createdPost);
//    }
//    @PostMapping("/create")
//    public ResponseEntity<Post> createPost(@RequestBody @Valid PostDto postDto) {
//        Post createdPost = postService.createPost(postDto);
//        return ResponseEntity.ok(createdPost);
//    }



//    @PostMapping("/create")
//    public ResponseEntity<Post> createPost(@RequestBody Map<String, String> request) {
//        String content = request.get("content");
//        Post createdPost = postService.createPost(content);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
//    }

//    @GetMapping("/{postId}")
//    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
//        Post post = postService.getPostById(postId);
//        return ResponseEntity.ok(post);
//    }
//
//    @PutMapping("/modify/{postId}")
//    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @RequestBody Map<String, String> request) {
//        String content = request.get("content");
//        Post updatedPost = this.postService.getPostById(postId);
//        this.postService.updatePost(updatedPost,content);
//        return ResponseEntity.ok(updatedPost);
//    }
//
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
