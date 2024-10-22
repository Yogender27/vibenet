package com.vibenet.vibenet.services;

import com.vibenet.vibenet.exception.ChatException;
import com.vibenet.vibenet.models.Chat;
import com.vibenet.vibenet.models.User;
import com.vibenet.vibenet.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements ChatService{
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User user2) {
        Chat isExist = chatRepository.findChatByUsersId(user2,reqUser);
        if (isExist!=null){
            return  isExist;
        }
        Chat newChat = new Chat();
        newChat.getUsers().add(user2);
        newChat.getUsers().add(reqUser);
        newChat.setChatName(user2.getFirstName());
        newChat.setTimeStamp(LocalDateTime.now());

        return chatRepository.save(newChat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws ChatException {
        Optional<Chat> opt = chatRepository.findById(chatId);
        if (opt.isEmpty()){
            throw  new ChatException("Chat is not Exist "+chatId);
        }
        return opt.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        List<Chat>usersChat = chatRepository.findByUsersId(userId);
        return usersChat;
    }
}
