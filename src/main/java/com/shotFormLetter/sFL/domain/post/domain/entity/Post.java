package com.shotFormLetter.sFL.domain.post.domain.entity;

//import com.shotFormLetter.sFL.domain.user.domain.entity.User;
import com.shotFormLetter.sFL.domain.user.domain.entity.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "content", columnDefinition = "text")
    private String content;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
