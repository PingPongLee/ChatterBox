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
public class MessageChat extends Message
{
    private String chat;
    private String messageFrom;
    private String message;
    
    public String getChat()
    {
        return chat;
    }

    public void setChat(String chat)
    {
        this.chat = chat;
    }

    public String getMessageFrom()
    {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom)
    {
        this.messageFrom = messageFrom;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
    
    
}
