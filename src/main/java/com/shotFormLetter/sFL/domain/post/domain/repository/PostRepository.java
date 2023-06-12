package com.shotFormLetter.sFL.domain.post.domain.repository;

import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByUserName(String userName);
    Page<Post> findAll(Pageable pageable);
}
