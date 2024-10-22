package com.vibenet.vibenet.services;

import com.vibenet.vibenet.models.Post;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.repository.PostRepository;
import com.vibenet.vibenet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{
    @Autowired
   private PostRepository postRepository;
    @Autowired
  private   UserService userService;
    @Autowired
  private   UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer id) throws Exception {

        User user = userService.findByUserId(id);
        Post newPost = new Post();
        newPost.setCaption(post.getCaption());
        newPost.setMessage(post.getMessage());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);
        newPost.setCreatedAt(LocalDateTime.now());

        return postRepository.save(newPost);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {

        Post post = findPostById(postId);
        User user = userService.findByUserId(userId);

        if(post.getUser().getId()!=user.getId()){
            throw new Exception("You cannot delete another user posts");
        }
         postRepository.delete(post);
        return "Post deleted sucessfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {

        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> opt = postRepository.findById(postId);
        if ((opt.isEmpty())){
            throw new Exception("Post not found"+postId);
        }

        return opt.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);
        User user = userService.findByUserId(userId);
        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);

        }else {
            user.getSavedPost().add(post);
        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {

        Post post = findPostById(postId);
        User user = userService.findByUserId(userId);

        if(post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else {
        post.getLiked().add(user);}
        return postRepository.save(post);
    }
}
