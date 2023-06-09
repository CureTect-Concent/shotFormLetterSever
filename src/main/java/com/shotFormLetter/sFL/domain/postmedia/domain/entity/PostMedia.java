//package com.shotFormLetter.sFL.domain.postmedia.domain.entity;
//
//import com.shotFormLetter.sFL.domain.media.domain.entity.Media;
//import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Getter
//@Setter
//@Entity
//@Table(name="PostMedia")
//public class PostMedia {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int postMediaId;
//
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="post_id")
//    private Post post;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "media_id")
//    private Media media;
//
//    @Column(name="reference")
//    private int reference;
//}
