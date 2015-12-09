/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_client;

/**
 *
 * @author djda9
 */
public class ViewModel
{
    Connect comm;                

    
    public ViewModel(String nick, String ip, int port)
    {
        comm = new Connect(ip, port); 
        comm.connectToServer();
//        comm.getChatrooms();
//        comm.getUsers();
//        comm.getMessages();
    }
    
    
    
    
    
}
