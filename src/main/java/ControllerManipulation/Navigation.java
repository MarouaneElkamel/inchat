/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerManipulation;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Elkamel
 */
public class Navigation
{
    public static void goProfile()
    {
        Session.Session.getInstance().getProfile().start(Session.Session.getInstance().getStage());
        Session.Session.getInstance().getStage().centerOnScreen();
    }
    
    public static void goLogout()
    {
        Session.Session.getInstance().setConnectedUser(null);
        Session.Session.getInstance().getLogin().start(Session.Session.getInstance().getStage());
        Session.Session.getInstance().getStage().centerOnScreen();
    }
    
    public static void goFriendSearch()
    {
        Session.Session.getInstance().getFriendSearch().start(Session.Session.getInstance().getStage());
        Session.Session.getInstance().getStage().centerOnScreen();
    }
    
    public static void goFriendList()
    {
        Session.Session.getInstance().getFriendList().start(Session.Session.getInstance().getStage());
        Session.Session.getInstance().getStage().centerOnScreen();
    }
    
    public static void goConversation()
    {
        Session.Session.getInstance().getConversation().start(Session.Session.getInstance().getStage());
        Session.Session.getInstance().getStage().centerOnScreen();
    }
    
    public static void goClose()
    {
        Session.Session.getInstance().setConnectedUser(null);
        System.exit(0);
    }
    
    public static void goAbout()
    {
       showAbout();
    }
    
    public static void goSignin()
    {
        Session.Session.getInstance().getSignUp().start(Session.Session.getInstance().getStage());
        Session.Session.getInstance().getStage().centerOnScreen();
    }
    
    public static void goLogin(Model.User user)
    {
            Session.Session.getInstance().setConnectedUser(user);
            Session.Session.getInstance().getConversation().start(Session.Session.getInstance().getStage());
            Session.Session.getInstance().getStage().centerOnScreen();
    }
    
    public static void goBack()
    {
        Session.Session.getInstance().getLogin().start(Session.Session.getInstance().getStage());
        Session.Session.getInstance().getStage().centerOnScreen();
    }
    
    public static File goSelectAvatar()
    {
       
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        Stage s = new Stage();
        return fileChooser.showOpenDialog(s);
    }
     public static void showAbout()
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Inchat");
        alert.setHeaderText(null);
        alert.setContentText("Inchat is a messaging application that makes it easy to communicate with anyone \n                   Design by Proxym-IT ");
        alert.showAndWait();
        }
}
