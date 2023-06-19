package com.shotFormLetter.sFL.domain.post.domain.service;

import com.shotFormLetter.sFL.domain.post.domain.entity.Post;
import com.shotFormLetter.sFL.domain.post.dto.GetPostDto;
import com.shotFormLetter.sFL.domain.post.dto.ThumbnailPostDto;
import com.shotFormLetter.sFL.domain.post.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
<<<<<<< Updated upstream
import java.util.NoSuchElementException;
=======
import java.util.Optional;

>>>>>>> Stashed changes
@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;


    @Override
    public Post createPost(String content) {
        Post post = new Post();
        post.setContent(content);
<<<<<<< Updated upstream
        post.setCreatedAt(LocalDateTime.now());
        this.postRepository.save(post);
        return post;
    }

    @Override
    public Post getPostById(int postId) {
        return postRepository.findById(postId)
                .orElseThrow();
    }
=======
        post.setTitle(title);
        post.setMedia_reference(media_reference);
        post.setS3Urls(s3Urls);
        post.setUserId(member.getUserId());
        post.setCreatedAt(createdAt);
        postRepository.save(post);
        return post;
    }

//    @Override
//    public List<Post> PostByUserName(Member userName) {
//        List<Post> posts=postRepository.findByMember(userName);
//        List<Post> filteredPosts=new ArrayList<>();
//        for (Post post:posts){
//            Post filteredPost = new Post();
//            filteredPost.setPostId(post.getPostId());
//            filteredPost.setTitle(post.getTitle());
//            filteredPost.setContent(post.getContent());
//            filteredPost.setMedia_reference(post.getMedia_reference());
//            filteredPost.setCreatedAt(post.getCreatedAt());
//            filteredPost.setUserId(userName.getUserId());
////            filteredPost.setMember(userName);
//            filteredPost.setS3Urls(post.getS3Urls());
//            filteredPosts.add(filteredPost);
//        }
//        return filteredPosts;
//    }

    @Override
    public List<ThumbnailPostDto> PostByUserName(Member userName) {
        List<Post> posts=postRepository.findByMember(userName);
        List<ThumbnailPostDto> thumbnailPostDto= new ArrayList<>();
        for (Post post :posts){
            ThumbnailPostDto th_list= new ThumbnailPostDto();
            th_list.setPostId(post.getPostId());
            th_list.setUserId(userName.getUserId());
            th_list.setTitle(post.getTitle());
            th_list.setCreatedAt(post.getCreatedAt());
            thumbnailPostDto.add(th_list);
        }
        return thumbnailPostDto;
//        List<Post> filteredPosts=new ArrayList<>();
//        for (Post post:posts){
//            Post filteredPost = new Post();
//            filteredPost.setPostId(post.getPostId());
//            filteredPost.setTitle(post.getTitle());
////            filteredPost.setContent(post.getContent());
////            filteredPost.setMedia_reference(post.getMedia_reference());
//            filteredPost.setCreatedAt(post.getCreatedAt());
//            filteredPost.setUserId(userName.getUserId());
////            filteredPost.setMember(userName);
////            filteredPost.setS3Urls(post.getS3Urls());
//            filteredPosts.add(filteredPost);
//        }
//        return filteredPosts;
    }

    @Override
    public GetPostDto getPost(Member member, Long postId) {
        Post getPost=postRepository.getPostByPostId(postId);
        GetPostDto getPostDto =new GetPostDto();
        getPostDto.setPostId(getPost.getPostId());
        getPostDto.setTitle(getPost.getTitle());
        getPostDto.setContent(getPost.getContent());
        getPostDto.setS3Urls(getPost.getS3Urls());
        getPostDto.setMedia_reference(getPost.getMedia_reference());
        getPostDto.setUserId(member.getUserId());
        getPostDto.setUserPkId(member.getId());
        getPostDto.setCreatedAt(getPost.getCreatedAt());
        return getPostDto;
//        getPost.setPostId(postId);
//        getPost.setTitle(getPost.getTitle());
//        getPost.setContent(getPost.getContent());
//        getPost.setS3Urls(getPost.getS3Urls());
//        getPost.setMedia_reference(getPost.getMedia_reference());
//        getPost.setCreatedAt(getPost.getCreatedAt());
//        getPost.setUserId(getPost.getUserId());
    }


//
//    @Override
//    public Post getPostByPostId(Long postId){
//        return postRepository.getPostByPostId(postId);
//    }
>>>>>>> Stashed changes

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public void updatePost(Post post, String content) {
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        this.postRepository.save(post);
    }




    @Override
    public void deletePost(int postId) {
        if (!postRepository.existsById(postId)) {
            throw new NoSuchElementException("Post not found");
        }
        postRepository.deleteById(postId);
    }
}
