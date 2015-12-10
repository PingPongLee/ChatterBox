/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_client;

import chatterbox_socketlayout.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javax.swing.JScrollBar;

/**
 *
 * @author Da9L
 */
public class HandleDataFromServer extends Thread 
{
    Message msg;
    DataOutputStream outputStream;      
    ChatterBox_Client view;
    
//    public HandleDataFromServer(Message msg, DataOutputStream outputStream, ChatRoomHandler chatRoomHandler, ClientHandler clientHandler)
//    {
//        this.msg = msg;
//        this.outputStream = outputStream;
//        this.chatRoomHandler = chatRoomHandler;
//        this.clientHandler = clientHandler;
//    }
    public HandleDataFromServer(Message msg, ChatterBox_Client view)
    {
        this.msg = msg;
        this.view = view;
    }
    
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
                        break;
                    case 2: //Requst for chatroom
                        break;
                    case 3: //Request for private conversation
                        break;
                    case 4: // Get users
                        addUsersToList();
                        break; 
                    case 5: // Get chatrooms
                        addChatRoomsToList();
                        break; 
                    case 6: //Create chatroom                        
                        break;
                    default:
                        break;
                }
                break;
            case Chat:
                switch (msg.getSubType())
                {
                    case 1: //Public message
                        addMessageToList();
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
    
    private void addUsersToList()
    {                     
        String data = ((MessageSetting)msg).getChat();                
        ObservableList<String> items = FXCollections.observableArrayList (data.split(";"));
        view.users.setItems(items);
    }

    private void addChatRoomsToList()
    {
        String data = ((MessageSetting)msg).getChat();                
        ObservableList<String> items = FXCollections.observableArrayList (data.split(";"));
        view.chatrooms.setItems(items);     
    }
    
    private synchronized void addMessageToList()
    {
        MessageChat message = (MessageChat)msg;
        GridPane newMsg = new GridPane();
        newMsg.setHgap(15);
        newMsg.setPadding(new Insets(10,10,10,10));
        if(!message.getMessageFrom().equals(view.getUserNicK()))
        {            
            newMsg.setStyle("-fx-background-color: #ccffff;-fx-background-radius: 5.0;");
            newMsg.setAlignment(Pos.CENTER_RIGHT);                        
            newMsg.add(new Label(message.getTime().toString()),2,0);
            newMsg.add(new Label(message.getMessageFrom()),1,0);
            newMsg.add(new Label(message.getMessage()),0,0);            
        }
        else
        {
            newMsg.setStyle("-fx-background-color: #66ffff;-fx-background-radius: 5.0;");
            newMsg.setAlignment(Pos.CENTER_LEFT);                        
            newMsg.add(new Label(message.getTime().toString()),0,0);
            newMsg.add(new Label(message.getMessageFrom()),1,0);
            newMsg.add(new Label(message.getMessage()),2,0);        
        }        
        Platform.runLater(() -> view.chatHistory.getChildren().add(newMsg));      
    }
    
//    private void requstForChatroom()
//    {
//        ChatHandler chat = chatRoomHandler.getChatRoomByName(((MessageChat)msg).getChat());
//        for (MessageChat messageChat : chat.getChatList()) 
//        {
//            try 
//            {
//                outputStream.writeUTF(new MessageHandler().convertMessageToSocketString(messageChat));
//            } 
//            catch (IOException ex) 
//            {
//                ex.printStackTrace();
//            } 
//            catch (Exception ex) 
//            {
//                ex.printStackTrace();
//            }
//        }
//    }
//    
//    private void handleCreateChatroom()
//    {
//        String allChatroomsName = "";
//        String sendString = "";
//        chatRoomHandler.addChatRoom(((MessageChat)msg).getChat());
//        
//        for (ChatHandler chatRoom : chatRoomHandler.getChatRooms()) 
//        {
//            if (allChatroomsName != "") 
//            {
//                allChatroomsName += ";";
//            }
//            allChatroomsName += chatRoom.getChatName();
//        }
//        
//        MessageSetting sendMsg = new MessageSetting();
//        sendMsg.setType(messageType.setting);
//        sendMsg.setSubType(5); //Get chatroom
//        sendMsg.setTime(new Date());
//        sendMsg.setChat(allChatroomsName);
//        try 
//        {
//            sendString = new MessageHandler().convertMessageToSocketString(sendMsg);
//        } 
//        catch (Exception e) 
//        {
//            System.out.println("Convert did not work: " + e.getMessage());
//        }        
//        
//        for (ClientSocket clientSocket : clientHandler.getClientSockets()) 
//        {
//            if (!clientSocket.getOutputStream().equals(outputStream)) 
//            {
//                try {
//                    clientSocket.getOutputStream().writeUTF(sendString);
//                } catch (IOException ex) {
//                   ex.printStackTrace();
//                }
//            }
//        }
//    }
}
