package com.vibenet.vibenet.services;

import com.vibenet.vibenet.models.Comment;
import com.vibenet.vibenet.models.Post;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.repository.CommentRepository;
import com.vibenet.vibenet.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
   @Autowired
    private PostService postService;
   @Autowired
   private UserService userService;
   @Autowired
   private PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
        User user = userService.findByUserId(userId);
        Post post = postService.findPostById(postId);
        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        Comment savedComment =commentRepository.save(comment);

        post.getComments().add(comment);

        postRepository.save(post);

        return savedComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> comment= commentRepository.findById(commentId);
        if (comment.isEmpty()){
            throw  new Exception("comment not exit");
        }
        return comment.get();
    }

    @Override
    public Comment likedComment(Integer commentId, Integer userId) throws Exception {
        Comment comment = findCommentById(commentId);
        User user = userService.findByUserId(userId);

        if(!comment.getLikes().contains(user)){
            comment.getLikes().add(user);
        }
        else {
            comment.getLikes().remove(user);
        }
        return commentRepository.save(comment);
    }
}
