package com.vibenet.vibenet.services;

import com.vibenet.vibenet.exception.ChatException;
import com.vibenet.vibenet.models.Chat;
import com.vibenet.vibenet.models.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser, User user2);
    public Chat findChatById(Integer chatId) throws ChatException;
    public List<Chat>findUsersChat(Integer userId);
}
