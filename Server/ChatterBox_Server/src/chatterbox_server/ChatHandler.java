/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_server;

import chatterbox_socketlayout.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ReneNN
 */
public class ChatHandler
{
    private List<MessageChat> chatList;
    private String chatName;
    
    public ChatHandler(String chatName)
    {
        this.chatName = chatName;
        chatList = new ArrayList<MessageChat>();
    }
    
    public synchronized void addChatToList(MessageChat chat)
    {
        chatList.add(chat);
    }
    
    public synchronized List<MessageChat> getChatList()
    {
        Collections.sort(chatList, new CustomComparator());
        return chatList;
    }
    
    public String getChatName()
    {
        return chatName;
    }
    public class CustomComparator implements Comparator<MessageChat> {
    @Override
    public int compare(MessageChat o1, MessageChat o2) {
        return o1.getTime().compareTo(o2.getTime());
    }
}
    
}
