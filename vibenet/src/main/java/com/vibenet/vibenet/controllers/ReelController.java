package com.vibenet.vibenet.controllers;

import com.vibenet.vibenet.models.Reel;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.services.ReelService;
import com.vibenet.vibenet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelController {

    @Autowired
    private ReelService reelService;
    @Autowired
    private UserService userService;


    @PostMapping("/api/reel")
    public Reel createReel(@RequestBody Reel reel, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserByJwt(jwt);

        Reel createdReel = reelService.createReel(reel, user.getId());
        return  createdReel;

    }
    @GetMapping("/api/reel")
    public List<Reel> findAllReel() throws Exception {
        return reelService.findAll();

    }
    @GetMapping("/api/reels/user/{userId}")
    public List<Reel>findUserReel(@PathVariable Integer userId) throws Exception {
        return  reelService.findUserReel(userId);
    }
}
