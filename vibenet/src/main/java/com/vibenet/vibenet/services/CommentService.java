package com.vibenet.vibenet.services;

import com.vibenet.vibenet.models.Comment;
import com.vibenet.vibenet.models.User;

public interface CommentService {
    public Comment createComment(Comment comment, Integer postId,Integer userId) throws Exception;
    public Comment findCommentById(Integer commentId) throws Exception;
    public Comment likedComment(Integer commentId,Integer userId) throws Exception;
}
