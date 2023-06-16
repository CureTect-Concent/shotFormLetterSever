package com.shotFormLetter.sFL.domain.post.domain.entity;

//import com.shotFormLetter.sFL.domain.user.domain.entity.User;
//import com.shotFormLetter.sFL.domain.member.domain.entity.SiteUser;
import com.shotFormLetter.sFL.domain.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name="title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 매핑할 외래키 이름
    private Member member;

    @ElementCollection
    @Column(name="s3Urls")
    @CollectionTable(name = "post_s3_urls", joinColumns = @JoinColumn(name = "post_id"))
    private List<String> s3Urls;

    @Column(name="media_reference")
    @ElementCollection
    private List<String> media_reference;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

}
