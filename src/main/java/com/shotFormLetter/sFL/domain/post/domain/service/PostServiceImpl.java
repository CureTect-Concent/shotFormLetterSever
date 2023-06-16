package com.shotFormLetter.sFL.domain.post.domain.service;

import com.shotFormLetter.sFL.domain.member.domain.Member;
import com.shotFormLetter.sFL.domain.member.domain.MemberRepository;
import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import com.shotFormLetter.sFL.domain.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    @Override
    public Post createPost(String title,String content,List<String> media_reference,Member nowUserId,List<String> s3Urls) {
        String id=nowUserId.getUserId();
        Optional<Member> optionalMember = memberRepository.findByUserId(id);
        Member member = optionalMember.get();
        LocalDateTime createdAt = LocalDateTime.now();
        Post post = new Post();
        post.setContent(content);
        post.setTitle(title);
        post.setMedia_reference(media_reference);
        post.setS3Urls(s3Urls);
        post.setMember(member);
        post.setCreatedAt(createdAt);
        return postRepository.save(post);
    }

//    @Override
//    public List<Post> PostByUserName(Member userName) {
//        List<Post> posts=postRepository.getPostByUserName(userName);
//        userName.
//        List<Post> filteredPosts=new ArrayList<>();
//        for (Post post:posts){
//            Post filteredPost = new Post();
//            filteredPost.setPostId(post.getPostId());
//            filteredPost.setTitle(post.getTitle());
//            filteredPost.setContent(post.nowUserName(userName));
//            filteredPost.setUserName(userName.getUserId());
//            filteredPosts.add(filteredPost);
//        }
//        return filteredPosts;
//    }
//
//    @Override
//    public Post getPostByPostId(Long postId){
//        return postRepository.getPostByPostId(postId);
//    }

//    @Override
//    public Post updatePost(Post post,PostDto postDto) {
//        String content = postDto.getContent();
//        post.setCreatedAt(LocalDateTime.now());
//        post.setContent(content);
//        return this.postRepository.save(post);
//    }




//    @Override
//    public void deletePost(Long postId) {
//        if (!postRepository.existsById(postId)) {
//            throw new NoSuchElementException("Post not found");
//        }
//        postRepository.deleteById(postId);
//    }
}
