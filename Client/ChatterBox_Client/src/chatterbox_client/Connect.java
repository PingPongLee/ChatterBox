/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_client;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import chatterbox_socketlayout.*;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author djda9
 */
public class Connect
{
    String serverIp;
    int serverPort;
    String userNick;
    
    Socket client;
    
    ChatterBox_Client view;
    
    public Connect(String ip, int port, String nick, ChatterBox_Client vm)
    {
        serverIp = ip;
        serverPort = port;
        userNick = nick;
        view = vm;
        InputStream inputStream = null;
        DataInputStream dataInputStream = null;        
        System.out.println("Tilslutter til " + ip + " på port " + port);
        try
        {
            client = new Socket(ip, port);
            System.out.println("Forbundet til " + client.getRemoteSocketAddress());
            Listener listen = new Listener(client, vm);  
            listen.start();
            System.out.println("Listening...");
        } 
        catch (Exception ex)
        {           
            System.out.println("Problemer med at forbinde: " + ex.getMessage());
        }
    }
    
    public boolean connectToServer()
    {        
        MessageSetting conn = new MessageSetting();
        conn.setType(messageType.setting);
        conn.setSubType(1);
        conn.setTime(new Date());
        conn.setChat(userNick);
        return transmitData(conn);
    }        
    
    public boolean sendMessage(MessageChat msg)
    {
        return transmitData(msg);
    }
    
    public boolean getMessagesFromChatroom(MessageSetting chatroom)
    {
        return transmitData(chatroom);
    }
    
    public boolean getChatrooms()
    {
        MessageSetting chatrooms = new MessageSetting();
        chatrooms.setType(messageType.setting);
        chatrooms.setSubType(2);
        chatrooms.setTime(new Date());        
        return transmitData(chatrooms);
    }
    
    public boolean creeateNewChatroom(Message newChatRoom)
    {
        return transmitData(newChatRoom);
    }
    
    public boolean getUsers()
    {
        return false;
    }
    
    public boolean getMessages()
    {
        return false;
    }
    
    private boolean transmitData(Message msg)
    {
        String transmitPacket = "";
        try
        {
            MessageHandler mh = new MessageHandler();
            transmitPacket = mh.convertMessageToSocketString(msg);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(transmitPacket);    
            System.out.println("Transmit success");
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Failed to transmit data packet!");
            return false;
        }                                    
    }
    
    private Message retrieveData()
    {
        try
        {
            MessageHandler mh = new MessageHandler();
            mh.convertMessageToSocketString(new MessageChat());
        }
        catch(Exception e)
        {
            
        }    
        return null;
    }
    
}
