/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_server;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ReneNN
 */
public class ChatRoomHandler
{
    private List<ChatHandler> chatRooms;
    
    public ChatRoomHandler()
    {
        chatRooms = new ArrayList<ChatHandler>();
    }
    
    public synchronized void addChatRoom(String name)
    {
        ChatHandler chatHandler = new ChatHandler(name);
        chatRooms.add(chatHandler);
    }

    public List<ChatHandler> getChatRooms() {
        return chatRooms;
    }
    
    public ChatHandler getChatRoomByName(String name)
    {
        for (ChatHandler chatRoom : chatRooms)
        {
            if (chatRoom.getChatName().equals(name))
            {
                return chatRoom;
            }
        }
        return null;
    }
}
