package com.vibenet.vibenet.services;

import com.vibenet.vibenet.models.Story;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImplementation implements StoryService{

    @Autowired
    private  StoryRepository storyRepository;
    @Autowired
    private UserService userService;

    @Override
    public Story createStory(Story story, Integer userId) throws Exception {
        User user = userService.findByUserId(userId);
        Story newStory =new Story();
        newStory.setUser(user);
        newStory.setCaption(story.getCaption());
        newStory.setImage(story.getImage());
        newStory.setTimeStamp(LocalDateTime.now());
        return storyRepository.save(newStory);
    }

    @Override
    public String deleteStory(Integer storyId) {

        storyRepository.deleteById(storyId);

        return "Deleted Successfully";
    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) throws Exception {
        User user = userService.findByUserId(userId);

        return storyRepository.findByUserId(userId);
    }
}
