/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_client;

import chatterbox_socketlayout.MessageSetting;
import chatterbox_socketlayout.messageType;
import static java.lang.Integer.parseInt;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import static javafx.application.Platform.exit;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author djda9
 */
public class ChatroomAddView
{
    Stage window;
    Connect connection;  
    
    
    public ChatroomAddView(Connect comm)
    {
        connection = comm;
    }
    
    public void display()
    {
        Stage window = new Stage();    
        window.setTitle("Add new chatroom");       
              
        //Chatrooms and Users      
        Label roomName = new Label("Chatroom name");
        TextField txtRoomName = new TextField();           
        
        Button btnStart = new Button("Create");
        btnStart.setOnAction(e -> {                        
            MessageSetting newChatRoom = new MessageSetting();
            newChatRoom.setType(messageType.setting);
            newChatRoom.setSubType(6);
            newChatRoom.setChat(txtRoomName.getText());
            newChatRoom.setTime(new Date());
            connection.creeateNewChatroom(newChatRoom);
            window.close();
        });
        
        GridPane table = new GridPane();
        table.setHgap(10);
        table.setVgap(5);
        table.add(roomName, 1, 1);
        table.add(txtRoomName, 2, 1);
        table.add(btnStart, 2, 4);                
        
        StackPane pane = new StackPane();   
        pane.setPadding(new Insets(10,10,10,10));
        pane.getChildren().add(table);

        Scene scene = new Scene(pane);                         
        window.setScene(scene);
        window.show();
    }
    
}
