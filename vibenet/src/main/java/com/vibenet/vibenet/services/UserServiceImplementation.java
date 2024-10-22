package com.vibenet.vibenet.services;

import com.vibenet.vibenet.config.JwtProvider;
import com.vibenet.vibenet.exception.UserException;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
   private UserRepository userRepository;


    @Override
    public User registerUser(User user) {
       User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User findByUserId(Integer id) throws UserException {

        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }

        throw new UserException(user+"is not found");
    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
    }


    @Override
    public User followUser(Integer reqUserId, Integer userId2) throws UserException {
        User reqUser = findByUserId(reqUserId);
        User user2 = findByUserId(userId2);

        if (reqUser == null || user2 == null) {
            throw new UserException("User not found");
        }

        user2.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user2.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;
    }


    @Override
    public User updateUser(User user, Integer userId) throws UserException {

        Optional<User> user1 = userRepository.findById(userId);

        if (user1.isEmpty()){
            throw  new UserException("User not Found");
        }

        User oldUser = user1.get();

        if(user.getFirstName()!=null){
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null){
            oldUser.setLastName(user.getLastName());
        }
        if(user.getEmail()!= null){
            oldUser.setEmail(user.getEmail());
        }
        if(user.getGender()!=null){
            oldUser.setGender(user.getGender());
        }
        return userRepository.save(oldUser);

    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public List<User> getAllUser() {
        List<User>users= userRepository.findAll();
        return users;
    }

    @Override
    public User getUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        user.setPassword(null);

        return user;
    }
}
