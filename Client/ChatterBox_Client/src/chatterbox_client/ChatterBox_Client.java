/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_client;

import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Daniel Jørgensen & René Nielsen
 */
public class ChatterBox_Client
{
    BorderPane layout;    
    
    String userNick;
    String serverIp;
    int serverPort;        
    
    public ChatterBox_Client(String nick, String ip, int port)
    {
        userNick = nick;
        serverIp = ip;
        serverPort = port;
        
        ViewModel vm = new ViewModel(nick,ip,port);
        
    }
    
    public void display()
    {
        Stage window = new Stage();    
        window.setTitle("ChatterBox Client v1");
        
        // Menu
        Menu fileMenu = new Menu("File");
        
        MenuItem settings = new MenuItem("Settings");
        settings.setOnAction(e -> {
            System.out.println("hallo");
        });        
        fileMenu.getItems().add(settings);
        
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> {
            exit();
        });
        fileMenu.getItems().add(exit);
        
        MenuBar mb = new MenuBar();
        mb.getMenus().addAll(fileMenu);          
              
        //Chatrooms and Users
        ListView chatrooms = new ListView();
        chatrooms.setMaxWidth(100);
        ListView users = new ListView();
        users.setMaxWidth(100);
        
        VBox roomsUsers = new VBox();
        roomsUsers.getChildren().addAll(chatrooms, users);
        
        //Chat
        //TextArea output = new TextArea();
        //output.setEditable(false);                
        GridPane output = new GridPane();
        output.setHgap(10);
        output.setVgap(5);
        output.add(new Label("Date"),1,2);
        output.add(new Label("test"),1,2);
        output.add(new Label("Message"),2,2);
        
        output.add(new Label("Date"),1,2);
        output.add(new Label("otherUser"),4,3);
        output.add(new Label("Message2"),2,3);

        
        
        
        TextArea input = new TextArea();
        input.setMaxHeight(60);        
        Button btnSend = new Button("Send");
        btnSend.setMinWidth(50);
        btnSend.setMaxWidth(100);
        btnSend.setMaxHeight(60);
        HBox chatline = new HBox();
        chatline.getChildren().addAll(input,btnSend);
        
         
        VBox chatbox = new VBox();
        VBox.setVgrow(output, Priority.ALWAYS);
        VBox.setVgrow(input,Priority.NEVER);       
        chatbox.getChildren().addAll(output, chatline);                    
     
        // Putting all elements together
        layout = new BorderPane();
        layout.setMaxWidth(50);
        layout.setTop(mb);
        layout.setLeft(roomsUsers);
        layout.setCenter(chatbox);
        Scene scene = new Scene(layout, 400, 300);                
                
        window.setScene(scene);
        window.show();
    }        
    
}
