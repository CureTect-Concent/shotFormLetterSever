package com.shotFormLetter.sFL.domain.post.controller;

<<<<<<< Updated upstream
=======
import com.shotFormLetter.sFL.domain.member.domain.Member;
import com.shotFormLetter.sFL.domain.member.domain.MemberRepository;
import com.shotFormLetter.sFL.domain.member.token.JwtTokenProvider;
>>>>>>> Stashed changes
import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.dto.GetPostDto;
import com.shotFormLetter.sFL.domain.post.dto.ThumbnailPostDto;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import com.shotFormLetter.sFL.domain.post.domain.service.PostService;

<<<<<<< Updated upstream
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
=======
import com.shotFormLetter.sFL.domain.post.dto.PostDto;
import com.shotFormLetter.sFL.domain.post.s3.service.s3Service;
import lombok.RequiredArgsConstructor;
>>>>>>> Stashed changes
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< Updated upstream
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
=======
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

>>>>>>> Stashed changes

@Slf4j
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;
    @GetMapping()
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }


    @PostMapping("/create")
<<<<<<< Updated upstream
    public ResponseEntity<Post> createPost(@RequestBody Map<String, String> request) {
        String content = request.get("content");
        Post createdPost = postService.createPost(content);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
=======
    public ResponseEntity<Post> createPost(@RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("files") List<MultipartFile> newImageList,
                           @RequestParam("media_reference") List<String> media_reference,
                           @RequestHeader("X-AUTH-TOKEN") String token) {

        String userId = jwtTokenProvider.getUserPk(token);
        System.out.println(userId);
        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        if (!optionalMember.isPresent()) {
            throw new IllegalStateException("User not found");
        }
        Member member=optionalMember.get();
        StringBuilder responseBuilder = new StringBuilder();
        List<String> s3Urls=new ArrayList<>();
        for (MultipartFile file : newImageList) {
            String key = file.getOriginalFilename();
            try {
                String imageUrl = s3Service.uploadImage(convertMultipartFileToFile(file), key,userId);
                s3Urls.add(imageUrl);
                responseBuilder.append("File uploaded successfully. Image URL: ").append(imageUrl).append("\n");
                System.out.println(s3Urls);
            } catch (Exception e) {
                throw new IllegalStateException("이미지 전송 실패");
            }
        }
        Post createPost=postService.createPost(title,content,media_reference,member,s3Urls);
        return ResponseEntity.ok(createPost);
>>>>>>> Stashed changes
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable int postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

<<<<<<< Updated upstream
    @PutMapping("/modify/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable int postId, @RequestBody Map<String, String> request) {
        String content = request.get("content");
        Post updatedPost = this.postService.getPostById(postId);
        this.postService.updatePost(updatedPost,content);
        return ResponseEntity.ok(updatedPost);
=======
    @GetMapping("/find/{userId}")
    public List<ThumbnailPostDto> getPostsByUserId(@RequestHeader("X-AUTH-TOKEN") String token) {

        String tokenId = jwtTokenProvider.getUserPk(token);

        Optional<Member> optionalMember = memberRepository.findByUserId(tokenId);
        if (!optionalMember.isPresent()) {
            throw new IllegalStateException("User not found");
        }
        Member member = optionalMember.get();
        List<ThumbnailPostDto> post = postService.PostByUserName(member);
        return post;
    }

    @GetMapping("/find/{userId}/{postId}")
    public GetPostDto getThumbnailList(@RequestHeader("X-AUTH-TOKEN") String token,
                                       @PathVariable("postId") Long postId) {

        String tokenId = jwtTokenProvider.getUserPk(token);

        Optional<Member> optionalMember = memberRepository.findByUserId(tokenId);
        if (!optionalMember.isPresent()) {
            throw new IllegalStateException("User not found");
        }
        Member member = optionalMember.get();
        GetPostDto getpostDto=postService.getPost(member,postId);
        return getpostDto;
>>>>>>> Stashed changes
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable int postId) {
        Post post = postService.getPostById(postId);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
