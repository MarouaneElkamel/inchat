package starter;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controllers.ConversationController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Peggy Fisher
 */
public class Conversation extends Application
    {
    public Parent root = null;
    public Scene scene = null;

    {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/Conversation.fxml"));
    try
        {
        root = (Parent) fxmlLoader.load();
        } catch (IOException ex)
        {
        Logger.getLogger(Conversation.class.getName()).log(Level.SEVERE, null, ex);
        }
    Session.Session.getInstance().setConversationController((ConversationController) fxmlLoader.getController());
    Session.Session.getInstance().setConversation(this);
    scene = new Scene(root);
    }

    @Override
    public void start(Stage stage)
        {
        if (!DataManipulation.SessionManipulation.userConnected())
            {
            Session.Session.getInstance().getLogin().start(Session.Session.getInstance().getStage());
            }
        stage.setTitle("Conversation");
        stage.setScene(scene);
        stage.show();
        }
    }
