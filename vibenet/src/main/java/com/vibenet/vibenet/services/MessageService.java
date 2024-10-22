package com.vibenet.vibenet.services;

import com.vibenet.vibenet.models.Chat;
import com.vibenet.vibenet.models.Message;
import com.vibenet.vibenet.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message message) throws Exception;
    public List<Message>findChatsMessages(Integer chatId) throws Exception;
}
