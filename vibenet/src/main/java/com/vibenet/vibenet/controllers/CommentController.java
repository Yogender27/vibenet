package com.vibenet.vibenet.controllers;


import com.vibenet.vibenet.models.Comment;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.services.CommentService;
import com.vibenet.vibenet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    UserService userService;

    @PostMapping("/api/comments/post/{postId}")

    public Comment createComment(@RequestBody Comment comment, @PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserByJwt(jwt);
        Comment createdComment = commentService.createComment(comment,postId, user.getId());
        return  createdComment;
    }

    @PutMapping("/api/comments/like/{commentId}")

    public Comment likeComment( @PathVariable Integer commentId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserByJwt(jwt);
        Comment likeComment = commentService.likedComment(commentId, user.getId());
        return  likeComment;
    }
}
