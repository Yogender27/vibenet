package com.vibenet.vibenet.services;

import com.vibenet.vibenet.models.Story;

import java.util.List;

public interface StoryService {

    public Story createStory(Story story,Integer userId) throws Exception;
    public String deleteStory(Integer storyId);
    public List<Story>findStoryByUserId(Integer userId) throws Exception;
}
