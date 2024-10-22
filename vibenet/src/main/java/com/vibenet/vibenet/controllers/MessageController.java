package com.vibenet.vibenet.controllers;

import com.vibenet.vibenet.models.Message;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.services.MessageService;
import com.vibenet.vibenet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;


    @PostMapping("/api/message/chat/{chatId}")
    public Message createMessage(@RequestBody Message message, @PathVariable("chatId") Integer chatId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserByJwt(jwt);
        return messageService.createMessage(user,chatId,message);

    }
    @GetMapping("/api/message/chat/{chatId}")
    public List<Message> findChatsMessage(@PathVariable("chatId") Integer chatId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserByJwt(jwt);
        return messageService.findChatsMessages(chatId);

    }
}
