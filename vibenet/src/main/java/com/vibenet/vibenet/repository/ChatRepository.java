package com.vibenet.vibenet.repository;

import com.vibenet.vibenet.models.Chat;

import com.vibenet.vibenet.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
    public List<Chat>findByUsersId(Integer userId);
    @Query("SELECT c FROM Chat c WHERE :user1 MEMBER OF c.users AND :user2 MEMBER OF c.users")
    Chat findChatByUsersId(@Param("user1") User user1, @Param("user2") User user2);


}
