package com.shotFormLetter.sFL.domain.post.controller;

import com.shotFormLetter.sFL.ExceptionHandler.NotFoundException;
import com.shotFormLetter.sFL.domain.member.domain.Member;
import com.shotFormLetter.sFL.domain.member.domain.MemberRepository;
import com.shotFormLetter.sFL.domain.member.token.JwtTokenProvider;
import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import com.shotFormLetter.sFL.domain.post.domain.service.PostService;

import com.shotFormLetter.sFL.domain.post.dto.PostDto;
import com.shotFormLetter.sFL.domain.post.response.PostResponse;
import com.shotFormLetter.sFL.domain.post.s3.service.s3Service;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;




@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PostRepository postRepository;
    private final s3Service s3Service;


//    @GetMapping()
//    public ResponseEntity<List<Post>> getAllPosts() {
//        List<Post> posts = postService.getAllPosts();
//        return ResponseEntity.ok(posts);
//    }


    @PostMapping("/create")
    public void createPost(@RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("files") List<MultipartFile> newImageList,
                           @RequestParam("media_reference") List<String> media_reference,
                           @RequestHeader("X-AUTH-TOKEN") String token) {

        String userId = jwtTokenProvider.getUserPk(token);
        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        if (!optionalMember.isPresent()) {
            throw new IllegalStateException("User not found");
        }

    }


//        if (!optionalMember.isPresent()) {
//            throw new IllegalStateException("User not found");
//        }
//        Member nowId = optionalMember.get();
//        StringBuilder responseBuilder = new StringBuilder();
//        List<String> s3Urls=new ArrayList<>();
//        for (MultipartFile file : newImageList) {
//            String key = file.getOriginalFilename();
//            try {
//                String imageUrl = s3Service.uploadImage(convertMultipartFileToFile(file), key);
//                s3Urls.add(imageUrl);
//                responseBuilder.append("File uploaded successfully. Image URL: ").append(imageUrl).append("\n");
//                System.out.println(s3Urls);
//            } catch (Exception e) {
//            }
//        }
//        postService.createPost(title,content,media_reference,nowId,s3Urls);
//    }
//    public ResponseEntity<Post> createPost(@RequestParam("title") String title,
//                                           @RequestParam("content") String content,
//                                           @RequestParam("files") List<MultipartFile> newImageList,
//                                           @RequestParam("media_reference") List<String> media_reference,
//                                           @RequestHeader("X-AUTH-TOKEN") String token) {
//
////        String token = jwtTokenProvider.resolveToken(request);
//        String userId = jwtTokenProvider.getUserPk(token);
//        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
//        if (!optionalMember.isPresent()) {
//            throw new IllegalStateException("User not found");
//        }
//
//        Member member = optionalMember.get();
//        StringBuilder responseBuilder = new StringBuilder();
//        List<String> s3Urls=new ArrayList<>();
//        for (MultipartFile file : newImageList) {
//            String key = file.getOriginalFilename();
//            try {
//                String imageUrl = s3Service.uploadImage(convertMultipartFileToFile(file), key);
//                s3Urls.add(imageUrl);
//                responseBuilder.append("File uploaded successfully. Image URL: ").append(imageUrl).append("\n");
//                System.out.println(s3Urls);
//            } catch (Exception e) {
//                return null;
//            }
//        }
//        Post createdPost = postService.createPost(title,content,media_reference,member,userId,s3Urls);
//        return ResponseEntity.ok(createdPost);
//    }


    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
        }
        return file;
    }

//    @GetMapping("/find/{userId}")
//    public List<Post> getPostsByUserId(@PathVariable("userId") @Valid String userId, @RequestHeader("X-AUTH-TOKEN") String token) {
////        String token = jwtTokenProvider.resolveToken(request);
//
//        String nowId = jwtTokenProvider.getUserPk(token);
//
//        Optional<Member> optionalMember = memberRepository.findByUserId(nowId);
//        if (!optionalMember.isPresent()) {
//            throw new IllegalStateException("User not found");
//        }
//        Member member = optionalMember.get();
//        List<Post> posts = postService.PostByUserName(member);
//        return posts;
//    }

//    @GetMapping("/find/{userId}/{postId}")
//    public ResponseEntity<Post> getPostsByUserId(@PathVariable("userId") @Valid String userId,
//                                                 @PathVariable("postId")@Valid Long postId,
//                                                 HttpServletRequest request) {
//        String token = jwtTokenProvider.resolveToken(request);
//
//        String nowId = jwtTokenProvider.getUserPk(token);
//
//        Optional<Member> optionalMember = memberRepository.findByUserId(nowId);
//        if (!optionalMember.isPresent()) {
//            throw new IllegalStateException("User not found");
//        }
//        Member member = optionalMember.get();
//        Post post = postService.getPostByPostId(postId);
//        return ResponseEntity.ok(post);
//    }

//    @PutMapping("/modify/{postId}")
//    public ResponseEntity<Post> updatePost(@PathVariable Long postId,
//                                           @RequestBody @Valid PostDto postDto,
//                                           HttpServletRequest request) {
//        String token = jwtTokenProvider.resolveToken(request);
//        String userId = jwtTokenProvider.getUserPk(token);
//
//        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
//        if (!optionalMember.isPresent()) {
//            throw new IllegalStateException("User not found");
//        }
//
//        Member member = optionalMember.get();
//        Optional<Post> optionalPost = postRepository.findById(postId);
//        if (!optionalPost.isPresent()) {
//            throw new IllegalArgumentException("Post not found");
//        }
//        Post post = optionalPost.get();
//        // 작성자 ID와 토큰의 ID 값이 일치하는지 확인
//        if (!post.getUserName().getId().equals(member.getId())) {
//            throw new IllegalArgumentException("You do not have permission to update this post");
//        }
//
//        Post updatedPost=postService.updatePost(post,postDto);
//        return ResponseEntity.ok(updatedPost);
//    }
//
//    @DeleteMapping("/delete/{userId}/{postId}")
//    public ResponseEntity<Void> deletePost(@PathVariable("userId") @Valid String userId,
//                                           @PathVariable("postId")@Valid Long postId,
//                                           HttpServletRequest request) {
//        String token = jwtTokenProvider.resolveToken(request);
//
//        String nowId = jwtTokenProvider.getUserPk(token);
//
//        Optional<Member> optionalMember = memberRepository.findByUserId(nowId);
//        if (!optionalMember.isPresent()) {
//            throw new IllegalStateException("User not found");
//        }
//        Member member = optionalMember.get();
//        Post post = postService.getPostByPostId(postId);
//        if (post == null) {
//            return ResponseEntity.notFound().build();
//        }
//        postService.deletePost(postId);
//        return ResponseEntity.noContent().build();
//    }
}
