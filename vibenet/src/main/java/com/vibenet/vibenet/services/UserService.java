package com.vibenet.vibenet.services;

import com.vibenet.vibenet.exception.UserException;
import com.vibenet.vibenet.models.User;

import java.util.List;

public interface UserService {

    public User registerUser(User user);
    public User findByUserId(Integer id) throws UserException;
    public User findByEmail(String email);
    public  User followUser(Integer userId1,Integer userId2) throws UserException;
    public User updateUser(User user, Integer userId) throws UserException;
    public List<User>searchUser(String query);
    public List<User>getAllUser();
    public User getUserByJwt(String jwt);
}
