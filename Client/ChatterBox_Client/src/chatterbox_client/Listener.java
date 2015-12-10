/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_client;

import chatterbox_socketlayout.Message;
import chatterbox_socketlayout.MessageChat;
import chatterbox_socketlayout.MessageHandler;
import chatterbox_socketlayout.ViewMessage;
import chatterbox_socketlayout.messageType;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author djda9
 */
public class Listener extends Thread
{
    Socket socket;
    MessageHandler msgHandler;   
    ChatterBox_Client view;
    
    public Listener(Socket socket, ChatterBox_Client view) 
    {
        this.socket = socket;
        this.view = view;
        msgHandler = new MessageHandler();
    }    
    
    public void run()
    {
        InputStream inputStream = null;
        DataInputStream dataInputStream = null;
        try
        {
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
        }
        catch(Exception e)
        {
            
        }          
        String input;
        while (true) 
        {
            try 
            {
                input = dataInputStream.readUTF();
                if ((input != null)) 
                {                    
                    Message msg = msgHandler.convertSocketStringToMessage(input);
                    new HandleDataFromServer(msg, view).start();
//                    if(msg.getType() == messageType.Chat)
//                    {
//                        vc.showNewMessage((MessageChat)msg, true);
//                    }  
//                    if(msg.getType() == messageType.setting)
//                    {
//                         
//                    }
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
                return;
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }        
    }

}