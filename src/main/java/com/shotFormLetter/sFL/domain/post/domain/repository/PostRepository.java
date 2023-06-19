package com.shotFormLetter.sFL.domain.post.domain.repository;

import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Integer> {

<<<<<<< Updated upstream
=======
//    List<Post> findPostUserName(Member userName);
//    List<Post> getPostByUserName(String userId);
//
    Post getPostByPostId(Long postId);

//    List<Post> getPostByUserId(Member member);

    List<Post> findByMember(Member member);

//    @Query("SELECT new com.example.dto.PostDTO(p.postId, p.title, p.content) FROM Post p WHERE p.member = :member")
//    List<PostDTO> findByMember(@Param("member") Member member);
    List<Post> findAll();
>>>>>>> Stashed changes
}
