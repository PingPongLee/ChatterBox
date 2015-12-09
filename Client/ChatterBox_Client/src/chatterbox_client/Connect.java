/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import chatterbox_socketlayout.*;
import java.util.Date;

/**
 *
 * @author djda9
 */
public class Connect
{
    String serverIp;
    int serverPort;
    
    Socket client;
    
    public Connect(String ip, int port)
    {
        serverIp = ip;
        serverPort = port;
        System.out.println("Tilslutter til " + ip + " på port " + port);
        try
        {
            client = new Socket(ip, port);
            System.out.println("Forbundet til " + client.getRemoteSocketAddress());
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
        return transmitData(conn);
    }        
    
    public boolean getChatrooms()
    {
        MessageSetting chatrooms = new MessageSetting();
        chatrooms.setType(messageType.setting);
        chatrooms.setSubType(2);
        chatrooms.setTime(new Date());        
        return transmitData(chatrooms);
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
            return true;
        }
        catch(Exception e)
        {
            return false;
        }                                    
    }
    
    private String retrieveData()
    {
        try
        {
            MessageHandler mh = new MessageHandler();
            mh.convertMessageToSocketString(new MessageChat());
        }
        catch(Exception e)
        {
            
        }       
        return "";
    }
    
}
