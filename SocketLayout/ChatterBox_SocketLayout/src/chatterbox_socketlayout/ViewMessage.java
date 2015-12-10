/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_socketlayout;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 *
 * @author Da9L
 */
public class ViewMessage {

    VBox chatBox;
        
    public ViewMessage(VBox chatBox)
    {
        this.chatBox = chatBox;
    }
    
    public void showNewMessage(MessageChat msg, boolean fromNetwork)
    {
        GridPane newMsg = new GridPane();
        newMsg.setHgap(10);        
        if(fromNetwork)
        {            
            newMsg.setAlignment(Pos.CENTER_RIGHT);                        
            newMsg.add(new Label(msg.getTime().toString()),2,0);
            newMsg.add(new Label(msg.getMessageFrom()),1,0);
            newMsg.add(new Label(msg.getMessage()),0,0);            
        }
        else
        {
            newMsg.setAlignment(Pos.CENTER_LEFT);                        
            newMsg.add(new Label(msg.getTime().toString()),2,0);
            newMsg.add(new Label(msg.getMessageFrom()),1,0);
            newMsg.add(new Label(msg.getMessage()),0,0);        
        }        
        chatBox.getChildren().add(newMsg);
    }      
    
}
