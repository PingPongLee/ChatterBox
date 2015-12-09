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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ReneNN
 */
public class ClientSocket extends Thread
{
    private Socket socket;
    private ChatHandler chatHandler;
    private MessageHandler msgHandler;

    public ClientSocket(Socket socket, ChatHandler chatHandler) 
    {
        this.socket = socket;
        this.chatHandler = chatHandler;
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
