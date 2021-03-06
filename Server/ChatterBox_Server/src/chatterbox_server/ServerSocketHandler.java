package chatterbox_server;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ReneNN
 */
public class ServerSocketHandler extends Thread
{
    int port;
    ChatRoomHandler chatRoomHandler;
    ClientHandler clientHandler;
    
    public ServerSocketHandler(int port, ChatRoomHandler chatRoomHandler, ClientHandler clientHandler)
    {
        this.port = port;
        this.chatRoomHandler = chatRoomHandler;
        this.clientHandler = clientHandler;
    }
    
    public void run()
    {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try 
        {
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        while (true) 
        {
            try 
            {
                socket = serverSocket.accept();
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
                continue;
            }
            // Add new client to list and start lisning to the client
            ClientSocket cs = new ClientSocket(socket, chatRoomHandler, clientHandler);
            clientHandler.addClientSocketToList(cs);
            cs.start();
        }
    }
}
