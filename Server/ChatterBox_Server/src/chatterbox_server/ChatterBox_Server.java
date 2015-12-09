/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatterbox_server;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Daniel Jørgensen & René Nielsen
 */
public class ChatterBox_Server
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        StartServerView view = new StartServerView();
        view.launch(args);
    }
    
}
