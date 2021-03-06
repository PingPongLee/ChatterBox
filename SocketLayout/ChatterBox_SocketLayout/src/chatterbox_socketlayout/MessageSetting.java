/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_socketlayout;

/**
 *
 * @author Daniel Jørgensen & René Nielsen
 */
public class MessageSetting extends Message
{
    /* Subtypes
    1 : Request all users and chatrooms (initial connection) AND send own nick
    2 : Request chatroom messages
    3 : Request private conversation    
    4 : Get users
    5 : Get chatrooms
    6 : Create chatroom
    
    
    */
    private String chat;

    public String getChat()
    {
        return chat;
    }

    public void setChat(String chat)
    {
        this.chat = chat;
    }
    
    
}
