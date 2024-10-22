package com.vibenet.vibenet.controllers;

import com.vibenet.vibenet.exception.UserException;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
  private   UserService userService;



    @GetMapping("/api/users")
    public List<User>getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/api/users/{userId}")

    public  User getUserById(@PathVariable Integer userId) throws UserException {

        return userService.findByUserId(userId);
    }

    @PutMapping("/api/users/update")
    public  User UpdateUser(@RequestBody User user ,@RequestHeader ("Authorization") String jwt) throws UserException {
        User oldUser = userService.getUserByJwt(jwt);

        return  userService.updateUser(user, oldUser.getId());
    }

    @PutMapping("/api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt,@PathVariable("userId2") Integer userId2) throws UserException {
        User reqUser = userService.getUserByJwt(jwt);
        return userService.followUser(reqUser.getId(), userId2);
    }

    @GetMapping("/api/users/search")

    public List<User>searchUser(@RequestParam ("query") String query){
        List<User>users = userService.searchUser(query);
        return users;
    }

    @GetMapping("/api/users/profile")

    public User getUserFromToken(@RequestHeader("Authorization") String jwt){

        return userService.getUserByJwt(jwt);

    }




}
