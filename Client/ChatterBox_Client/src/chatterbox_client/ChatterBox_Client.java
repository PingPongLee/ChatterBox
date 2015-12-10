/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_client;

import chatterbox_socketlayout.MessageChat;
import chatterbox_socketlayout.MessageSetting;
import chatterbox_socketlayout.ViewChat;
import chatterbox_socketlayout.ViewMessage;
import chatterbox_socketlayout.messageType;
import java.util.Date;
import static javafx.application.Platform.exit;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
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
    ViewMessage vm;
    ViewChat vc;
    Connect comm; 
    
    BorderPane layout;        
    VBox chatHistory = new VBox();
    ListView<String> chatrooms = new ListView<String>();
    ListView users = new ListView();
    ScrollPane chatContent = new ScrollPane();
    
    String dongding;
    
    String selectedChatroom;
    
    String userNick;
    String serverIp;
    int serverPort;   

    public ChatterBox_Client(String nick, String ip, int port)
    {
        userNick = nick;
        serverIp = ip;
        serverPort = port;
        
        vm = new ViewMessage(chatHistory);
        //vc = new ViewChat();
        
        comm = new Connect(ip, port, nick, this); 
        comm.connectToServer(); 
    }
    
    public void display()
    {
        Stage window = new Stage();    
        window.setTitle("ChatterBox Client v1");
        
        // Menu
        Menu fileMenu = new Menu("File");
        
        MenuItem addChatroom = new MenuItem("Create chatroom");
        addChatroom.setOnAction(e -> {
            ChatroomAddView cav = new ChatroomAddView(comm);
            cav.display();
        });        
        fileMenu.getItems().add(addChatroom);
        
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
        //ListView<String> chatrooms = new ListView<String>();
        chatrooms.setMaxWidth(100); 
        
        chatrooms.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) 
            {
                chatHistory.getChildren().clear();
                selectedChatroom = newValue;
                MessageSetting chatRoomData = new MessageSetting();
                chatRoomData.setType(messageType.setting);
                chatRoomData.setSubType(2);
                chatRoomData.setTime(new Date());
                chatRoomData.setChat(newValue);
                comm.getMessagesFromChatroom(chatRoomData);
                vc = new ViewChat();
                vc.showChatRooms();
            }
        });
        
        //ListView users = new ListView();
        users.setMaxWidth(100);
        
        VBox roomsUsers = new VBox();
        roomsUsers.getChildren().addAll(new Label("Chatrooms"), chatrooms, new Label("Users"), users);
        
        //Chat                           
        //chatHistory.setFillWidth(true);        
        chatHistory.setSpacing(20);        
        chatHistory.setPadding(new Insets(10,10,10,10));  
        chatHistory.heightProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldvalue, Object newValue) 
            {               
                chatContent.setVvalue((Double)newValue );  
            }
        });
                 
        //Scrollpane
        chatContent.setFitToHeight(true);
        chatContent.setFitToWidth(true);    
        chatContent.setContent(chatHistory);                
                
        
        TextArea input = new TextArea();
        input.setMaxHeight(60);        
        Button btnSend = new Button("Send");
        btnSend.setOnAction(e -> {
            MessageChat newUserMsg = new MessageChat();
            newUserMsg.setType(messageType.Chat);
            newUserMsg.setSubType(1);
            newUserMsg.setChat(selectedChatroom);
            newUserMsg.setMessage(input.getText());            
            newUserMsg.setTime(new Date());
            newUserMsg.setMessageFrom(userNick);
            comm.sendMessage(newUserMsg);
            //vm.showNewMessage(newUserMsg, false);
        });
        btnSend.setMinWidth(50);
        btnSend.setMaxWidth(100);
        btnSend.setMaxHeight(60);
        HBox chatline = new HBox();
        chatline.getChildren().addAll(input,btnSend);            
     
        // Putting all elements together
        layout = new BorderPane();
        layout.setMaxWidth(50);
        layout.setTop(mb);
        layout.setLeft(roomsUsers);
        layout.setCenter(chatContent);
        layout.setBottom(chatline);
        Scene scene = new Scene(layout, 400, 300);                
                
        window.setScene(scene);
        window.show();
    }        
    
    public String getUserNicK()
    {
        return userNick;
    }
    
    
}
