package com.vibenet.vibenet.controllers;

import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.response.ApiResponse;
import com.vibenet.vibenet.models.Post;
import com.vibenet.vibenet.services.PostService;
import com.vibenet.vibenet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
   private PostService postService;
    @Autowired
    private UserService userService;
  @PostMapping("/api/posts")
     public ResponseEntity<Post>createPost( @RequestHeader("Authorization") String jwt,@RequestBody Post post) throws Exception {
      User user = userService.getUserByJwt(jwt);
     Post createdPost =postService.createNewPost(post,user.getId());
     return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);

 }
 @DeleteMapping("/posts/{postId}")

  public   ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
     User user = userService.getUserByJwt(jwt);
      String message = postService.deletePost(postId,user.getId());
      ApiResponse res = new ApiResponse(message,true);

      return new ResponseEntity<>(res,HttpStatus.OK);
 }

 @GetMapping("/posts/{postId}")
    public ResponseEntity<Post>findPostById(@PathVariable Integer postId) throws Exception {

      Post post = postService.findPostById(postId);
      return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
 }

 @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>>findUserPosts( @PathVariable Integer userId){

      List<Post> post = postService.findPostByUserId(userId);
      return new ResponseEntity<>(post,HttpStatus.OK);

 }
    @GetMapping("/posts")
    public ResponseEntity<List<Post>>findAllPosts(){

        List<Post> post = postService.findAllPost();
        return new ResponseEntity<>(post,HttpStatus.OK);

    }

    @PutMapping("/posts/saved/{postId}")

    public ResponseEntity<Post>savedPost(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.getUserByJwt(jwt);
      Post post = postService.savedPost(postId,user.getId());
      return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }
    @PutMapping("/posts/like/{postId}")

    public ResponseEntity<Post>likePost(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.getUserByJwt(jwt);
        Post post = postService.likePost(postId,user.getId());
        return new ResponseEntity<>(post,HttpStatus.ACCEPTED);
    }


}
