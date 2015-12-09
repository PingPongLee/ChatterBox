/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_server;

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
public class StartServerView extends Application
{
    Stage window;
      
    @Override
    public void start(Stage primaryStage)
    {
        window = primaryStage;        
        window.setTitle("Start Server!"); 
              
        Label port = new Label("Server port");
        TextField txtPort = new TextField();
        txtPort.setText("4545");
        Button btnStart = new Button("Start Server");
        btnStart.setOnAction(e -> {                        
           ServerSocketHandler  ssh = new ServerSocketHandler(parseInt(txtPort.getText()), new ChatHandler(), new ClientHandler());
           ssh.start();
        });
        
        GridPane table = new GridPane();
        table.setHgap(10);
        table.setVgap(5);
        table.add(port, 1, 1);
        table.add(txtPort, 2, 1);
        table.add(btnStart, 2, 2);                
        
        StackPane pane = new StackPane();   
        pane.setPadding(new Insets(10,10,10,10));
        pane.getChildren().add(table);

        Scene scene = new Scene(pane);                         
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
}
