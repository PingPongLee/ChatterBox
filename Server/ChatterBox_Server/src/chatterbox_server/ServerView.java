/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_server;

import static javafx.application.Platform.exit;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ReneNN
 */
public class ServerView
{
    ClientHandler clientHandler;
    ServerSocketHandler ssHandler;
    ChatRoomHandler chatRoomHandler;
    int port;
    public ServerView(int port)
    {
        this.port = port;
        clientHandler = new ClientHandler();
        chatRoomHandler = new ChatRoomHandler();
        ssHandler = new ServerSocketHandler(port, chatRoomHandler, clientHandler);
    }
    public void display()
    {
        BorderPane layout;    
        Stage window = new Stage();    
        window.setTitle("Server on port: " + port);
        
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
        
        //Chat room
        VBox chatbox = new VBox(); 
        
        // Putting all elements together
        layout = new BorderPane();
        layout.setMaxWidth(50);
        layout.setTop(mb);
        layout.setLeft(roomsUsers);
        layout.setCenter(chatbox);
        Scene scene = new Scene(layout, 400, 300);                
                
        window.setScene(scene);
        window.show();
        ssHandler.start();
    }
}
