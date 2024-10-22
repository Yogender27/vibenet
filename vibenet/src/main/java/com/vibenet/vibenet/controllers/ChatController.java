package com.vibenet.vibenet.controllers;

import com.vibenet.vibenet.exception.ChatException;
import com.vibenet.vibenet.exception.UserException;
import com.vibenet.vibenet.models.Chat;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.request.ChatRequest;
import com.vibenet.vibenet.services.ChatService;
import com.vibenet.vibenet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/chats/{user2}")
    public Chat createChat(@RequestBody ChatRequest req,@RequestHeader("Authorization") String jwt) throws UserException {
        User user2 = userService.findByUserId(req.getUser2());
        User reqUser = userService.getUserByJwt(jwt);
        Chat chat = chatService.createChat(reqUser,user2);
        return chat;
    }
    @GetMapping("/api/chats")
    public List<Chat> findAllChat(@RequestHeader("Authorization") String jwt){
        User user = userService.getUserByJwt(jwt);

        return chatService.findUsersChat(user.getId());
    }

}
