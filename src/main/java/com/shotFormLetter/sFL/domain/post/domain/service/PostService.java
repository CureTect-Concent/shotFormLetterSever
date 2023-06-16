package com.shotFormLetter.sFL.domain.post.domain.service;

import com.shotFormLetter.sFL.domain.member.domain.Member;
import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import com.shotFormLetter.sFL.domain.post.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.util.List;

public interface PostService {
//    Post createPost(String title,String content,List<String> media_reference,Member userName);
    Post createPost(String title,String content,List<String> media_reference,Member nowUserId,List<String> s3Urls);
//    List<Post> PostByUserName(Member userName);

////    Post updatePost(Post post, PostDto postDto);
//
//    Post getPostByPostId(Long postId);
//    void deletePost(Long postId);

}
