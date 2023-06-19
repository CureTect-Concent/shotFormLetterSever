package com.shotFormLetter.sFL.domain.post.domain.service;

import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
<<<<<<< Updated upstream
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
=======
import com.shotFormLetter.sFL.domain.post.dto.GetPostDto;
import com.shotFormLetter.sFL.domain.post.dto.ThumbnailPostDto;
import com.shotFormLetter.sFL.domain.post.dto.PostDto;
>>>>>>> Stashed changes

import java.util.List;

public interface PostService {
<<<<<<< Updated upstream
    Post createPost(String content);
    Post getPostById(int postId);
    List<Post> getAllPosts();
    void updatePost(Post post, String content);
    void deletePost(int postId);
=======
//    Post createPost(String title,String content,List<String> media_reference,Member userName);
    Post createPost(String title,String content,List<String> media_reference,Member nowUserId,List<String> s3Urls);
    List<ThumbnailPostDto> PostByUserName(Member userName);

    GetPostDto getPost(Member member, Long postId);

////    Post updatePost(Post post, PostDto postDto);
//
//    Post getPostByPostId(Long postId);
//    void deletePost(Long postId);

>>>>>>> Stashed changes
}
