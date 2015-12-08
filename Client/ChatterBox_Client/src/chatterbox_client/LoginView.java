/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_client;

import static java.lang.Integer.parseInt;
import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Daniel Jørgensen & René Nielsen
 */
public class LoginView extends Application
{
    Stage window;
      
    @Override
    public void start(Stage primaryStage)
    {
        window = primaryStage;        
        window.setTitle("Welcome to ChatterBox!"); 
              
        //Chatrooms and Users      
        Label nick = new Label("Nickname");
        TextField txtNick = new TextField();        
        Label server = new Label("Server");
        TextField txtServer = new TextField();
        Label port = new Label("Port");
        TextField txtPort = new TextField();
        txtPort.setText("4545");
        Button btnStart = new Button("Start");
        btnStart.setOnAction(e -> {                        
            ChatterBox_Client cbc = new ChatterBox_Client(txtNick.getText(), txtServer.getText(), parseInt(txtPort.getText()));
            cbc.display();
        });
        
        GridPane table = new GridPane();
        table.setHgap(10);
        table.setVgap(5);
        table.add(nick, 1, 1);
        table.add(txtNick, 2, 1);
        table.add(server, 1, 2);
        table.add(txtServer, 2, 2);
        table.add(port, 1, 3);
        table.add(txtPort, 2, 3);
        table.add(btnStart, 2, 4);                
        
        StackPane pane = new StackPane();   
        pane.setPadding(new Insets(10,10,10,10));
        pane.getChildren().add(table);

        Scene scene = new Scene(pane);                         
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }    
    
}
