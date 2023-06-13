package com.shotFormLetter.sFL.domain.post.domain.entity;

//import com.shotFormLetter.sFL.domain.user.domain.entity.User;
//import com.shotFormLetter.sFL.domain.member.domain.entity.SiteUser;
import com.shotFormLetter.sFL.domain.member.domain.Member;
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
    @Column(name = "postId")
    private Long postId;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member userName;
}
