package com.vibenet.vibenet.services;

import com.vibenet.vibenet.models.Reel;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReelsServiceImplementation implements ReelService{
    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private   UserService userService;

    @Override
    public Reel createReel(Reel reel, Integer userId) throws Exception {
        User user = userService.findByUserId(userId);
        Reel createReel = new Reel();
        createReel.setUser(user);
        createReel.setTitle(reel.getTitle());
        createReel.setVideo(reel.getVideo());

        return reelsRepository.save(createReel);
    }

    @Override
    public List<Reel> findAll() {

        List<Reel>allReels = reelsRepository.findAll();
        return  allReels;

    }

    @Override
    public List<Reel> findUserReel(Integer userId) throws Exception {
        userService.findByUserId(userId);

        return  reelsRepository.findByUserId(userId);
    }
;}
