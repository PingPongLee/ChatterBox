/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author djda9
 */
public class Connect
{
    String serverIp;
    int serverPort;
    
    public Connect(String ip, int port)
    {
        serverIp = ip;
        serverPort = port;
    }
    
    public boolean connect()
    {        
        try
        {
            System.out.println("Tilslutter til " + serverIp + " på port " + serverPort);
            Socket client = new Socket(serverIp, 4545);
            System.out.println("Forbundet til " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("Server says " + in.readUTF());
            
            return true;
            //client.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
