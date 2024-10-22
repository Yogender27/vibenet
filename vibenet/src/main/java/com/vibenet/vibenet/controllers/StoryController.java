package com.vibenet.vibenet.controllers;

import com.vibenet.vibenet.models.Story;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.services.StoryService;
import com.vibenet.vibenet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @Autowired
     private StoryService storyService;
    @Autowired
     private UserService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserByJwt(jwt);
        return storyService.createStory(story, user.getId());
    }
    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUserStory(@PathVariable Integer userId) throws Exception {

        return storyService.findStoryByUserId(userId);
    }
}
