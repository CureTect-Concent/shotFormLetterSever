package com.shotFormLetter.sFL.domain.user.domain.entity;

import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name="user_nickname")
    private String user_nickname;

    @Column(name = "password")
    private String password;

}
