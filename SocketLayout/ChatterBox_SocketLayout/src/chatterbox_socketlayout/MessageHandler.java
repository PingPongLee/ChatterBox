/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_socketlayout;

import static java.lang.Integer.parseInt;
import java.util.List;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author ReneNN
 */
public class MessageHandler
{
    
    public Message convertSocketStringToMessage(String socketString) throws Exception
    {
        Message message;
        
        String[] socketStringArray = socketString.split(";");
        
        if(socketStringArray.length < 3)
        {
            throw new IllegalArgumentException("The length of the message is to low");
        }
        int intType = parseInt(socketStringArray[0]);
        messageType type = messageType.values()[intType];
        
        switch (type)
        {
            case error:
                message = new MessageError();
                throw new NotImplementedException();
                //break;
            case setting:
                message = new MessageSetting(); 
                message.setType(type);
                message.setSubType(parseInt(socketStringArray[1]));
                message.setTime(Message.dateFormat.parse(socketStringArray[2]));
                ((MessageSetting)message).setChat(socketStringArray[3]);
                break;
            case Chat:
                message = new MessageChat();
                message.setType(type);
                message.setSubType(parseInt(socketStringArray[1]));
                message.setTime(Message.dateFormat.parse(socketStringArray[2]));
                ((MessageChat)message).setChat(socketStringArray[3]);
                ((MessageChat)message).setMessageFrom(socketStringArray[4]);
                String messageTotal = socketStringArray[5];
                for (int i = 6; i < socketStringArray.length; i++)
                {
                    messageTotal += ";" + socketStringArray[i];
                }
                ((MessageChat)message).setMessage(messageTotal);
                break;
            default:
                throw new NotImplementedException();
        }
        return message;
    }
    
    public String convertMessageToSocketString(Message message) throws Exception
    {
        String socketString =  message.getType().ordinal() +
                ";" + message.getSubType() + 
                ";" + Message.dateFormat.format(message.getTime());
        switch (message.getType())
        {
            case error:
                throw new NotImplementedException();
                //break;
            case setting:
                socketString += ";" + ((MessageSetting)message).getChat();
                break;
            case Chat:
                socketString += ";" + ((MessageChat)message).getChat() +
                        ";" + ((MessageChat)message).getMessageFrom() +
                        ";" + ((MessageChat)message).getMessage();
                break;
            default:
                throw new NotImplementedException();
        }
        return socketString;
    }
}
