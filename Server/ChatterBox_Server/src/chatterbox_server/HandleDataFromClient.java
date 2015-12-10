/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_server;

import chatterbox_socketlayout.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ReneNN
 */
public class HandleDataFromClient extends Thread
{
    Message msg;
    ClientSocket client;
    DataOutputStream outputStream;
    ChatRoomHandler chatRoomHandler;
    ClientHandler clientHandler;
    
    public HandleDataFromClient(Message msg, ClientSocket client, ChatRoomHandler chatRoomHandler, ClientHandler clientHandler)
    {
        this.msg = msg;
        this.client = client;
        this.outputStream = client.getOutputStream();
        this.chatRoomHandler = chatRoomHandler;
        this.clientHandler = clientHandler;
    }
    
    @Override
    public void run ()
    {
        switch (msg.getType())
        {
            case error:
                System.out.println("Error");
                break;
            case setting:
                switch (msg.getSubType())
                {
                    case 1: //Requst for all users and chatrooms (initial connection) AND send own nick
                        requstForAllUsersAndChatrooms();
                        break;
                    case 2: //Requst for chatroom
                        requstForChatroom();
                        break;
                    case 3: //Request for private conversation
                        requstForPrivateConversation();
                        break;
                    case 6: //Create chatroom
                        handleCreateChatroom();
                        break;
                    default:
                        break;
                }
                break;
            case Chat:
                switch (msg.getSubType())
                {
                    case 1: //Public message
                        handlePublicMsg();
                        break;
                    case 2: //Private
                        break;
                    default:
                        break;
                }
                break;
            default:
                System.out.println("Error");
        }
    }
    
    private void requstForAllUsersAndChatrooms()
    {
        client.setClientName(((MessageSetting)msg).getChat());
        //Chatrooms
        String sendString = getAllChatroomsMsgString();
        sendChatMsg(sendString, outputStream);
        
        //users
        sendAllUsersToAllUsers();
    }
    
    public void sendAllUsersToAllUsers()
    {
        clientHandler.getClientSockets().stream().forEach((clientSocket) ->
        {
         sendChatMsg(getAllUsersMsgString(), clientSocket.getOutputStream());   
        });
    }
    
    private void requstForChatroom()
    {
        ChatHandler chat = chatRoomHandler.getChatRoomByName(((MessageSetting)msg).getChat());
        client.setChatRoomName(((MessageSetting)msg).getChat());
        chat.getChatList().stream().forEach((messageChat) -> 
        {
            sendChatMsg(messageChat, outputStream);
        });
    }
    
    private void requstForPrivateConversation()
    {
        
    }
    
    private void handleCreateChatroom()
    {
        chatRoomHandler.addChatRoom(((MessageSetting)msg).getChat());
        client.setChatRoomName(((MessageSetting)msg).getChat());
        
        String sendString = getAllChatroomsMsgString();
                
        clientHandler.getClientSockets().stream().forEach((clientSocket) -> 
        {
            sendChatMsg(sendString, clientSocket.getOutputStream());
        });
    }
    
    private void handlePublicMsg()
    {
        ChatHandler ch = chatRoomHandler.getChatRoomByName(((MessageChat)msg).getChat());
        ch.addChatToList((MessageChat)msg);
        try
        {
            String sendString = new MessageHandler().convertMessageToSocketString(msg);
            clientHandler.getClientSockets().stream().filter((c) -> c.getChatRoomName().equals(((MessageChat)msg).getChat())).forEach((clientSocket) -> 
            {
                sendChatMsg(sendString, clientSocket.getOutputStream());
            });
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
    }
    
    private String getAllUsersMsgString()
    { 
        String allUsersName = "";
        for (ClientSocket user : clientHandler.getClientSockets()) 
        {
            if (allUsersName != "") 
            {
                allUsersName += ";";
            }
            allUsersName += user.getClientName();
        }
        MessageSetting newMsg = new MessageSetting();
        newMsg.setType(messageType.setting);
        newMsg.setSubType(4); //Get users
        newMsg.setTime(new Date());
        newMsg.setChat(allUsersName);
        String returnString = "";
        try 
        {
            returnString = new MessageHandler().convertMessageToSocketString(newMsg);
        } 
        catch (Exception e) 
        {
            System.out.println("Convert did not work: " + e.getMessage());
        }
        return returnString;
    }
    
    private String getAllChatroomsMsgString()
    {
        String allChatroomsName = "";
        for (ChatHandler chatRoom : chatRoomHandler.getChatRooms()) 
        {
            if (allChatroomsName != "") 
            {
                allChatroomsName += ";";
            }
            allChatroomsName += chatRoom.getChatName();
        }
        
        MessageSetting newMsg = new MessageSetting();
        newMsg.setType(messageType.setting);
        newMsg.setSubType(5); //Get chatroom
        newMsg.setTime(new Date());
        newMsg.setChat(allChatroomsName);
        String returnString = "";
        try 
        {
            returnString = new MessageHandler().convertMessageToSocketString(newMsg);
        } 
        catch (Exception e) 
        {
            System.out.println("Convert did not work: " + e.getMessage());
        }
        return returnString; 
    }
    
    private void sendChatMsg(MessageChat messageChat, DataOutputStream os)
    {
        try 
        {
            os.writeUTF(new MessageHandler().convertMessageToSocketString(messageChat));
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
    private void sendChatMsg(String messageChat, DataOutputStream os)
    {
        try 
        {
            os.writeUTF(messageChat);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }
}
