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
    1 : Get all users, chatrooms with messages (initial connection)
    2 : Get chatroom
    3 : Get private conversation    
    
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
