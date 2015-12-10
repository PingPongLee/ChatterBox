/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import chatterbox_socketlayout.*;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ReneNN
 */
public class ClientSocket extends Thread
{
    private Socket socket;
    private ChatRoomHandler chatRoomHandler;
    private ClientHandler clientHandler;
    private MessageHandler msgHandler;
    private DataOutputStream outputStream = null;
    private String chatRoomName = "";
    private String clientName = "";

    public ClientSocket(Socket socket, ChatRoomHandler chatRoomHandler, ClientHandler clientHandler) 
    {
        this.socket = socket;
        this.chatRoomHandler = chatRoomHandler;
        this.clientHandler = clientHandler;
        msgHandler = new MessageHandler();
    }

    public DataOutputStream getOutputStream() 
    {
        return outputStream;
    }

    public String getChatRoomName() 
    {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) 
    {
        this.chatRoomName = chatRoomName;
    }

    public String getClientName()
    {
        return clientName;
    }

    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }
    
    

    public void run() 
    {
        InputStream inputStream = null;
        DataInputStream dataInputStream = null;
        
        try 
        {
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) 
        {
            return;
        }
        String line;
        while (true) 
        {
            try 
            {
                line = dataInputStream.readUTF();
                if ((line != null)) 
                {
                    Message msg = msgHandler.convertSocketStringToMessage(line);
                    new HandleDataFromClient(msg, this,chatRoomHandler, clientHandler).run();
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
                try
                {
                    clientHandler.removeClient(this);
                    new HandleDataFromClient(null, this,chatRoomHandler, clientHandler).sendAllUsersToAllUsers();
                    socket.close();
                    
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }
                return;
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
