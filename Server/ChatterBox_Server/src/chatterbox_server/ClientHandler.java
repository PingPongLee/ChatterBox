/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_server;

import chatterbox_socketlayout.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ReneNN
 */
public class ClientHandler
{
    private List<ClientSocket> clientSockets;
    public ClientHandler ()
    {
        clientSockets = new ArrayList<ClientSocket>();
    }
    
    public void addClientSocketToList(ClientSocket socket)
    {
        clientSockets.add(socket);
    }
    
    public List<ClientSocket> getClientSockets()
    {
        return clientSockets;
    }
}
