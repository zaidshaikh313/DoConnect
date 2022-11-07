package com.greatlearning.service;

import com.greatlearning.model.Chat;
import com.greatlearning.repos.ChatRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepos chatRepos;

    public Chat send(Chat chat){
        Chat chat1=new Chat();
        chat1.setUsername(chat.getUsername());
        chat1.setMessage(chat.getMessage());
        chatRepos.save(chat1);
        return chat1;

    }
    public List<Chat> get(){
        List<Chat> chats=chatRepos.findAll();
        return chats;
    }
}
