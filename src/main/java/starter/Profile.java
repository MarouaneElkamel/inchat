package starter;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controllers.ProfileController;
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
public class Profile extends Application
    {
    public Parent root = null;
    public Scene scene = null;

    {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/Profile.fxml"));
    try
        {
        root = (Parent) fxmlLoader.load();
        } catch (IOException ex)
        {
        Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
        }
    Session.Session.getInstance().setProfileController((ProfileController) fxmlLoader.getController());
    scene = new Scene(root);
    }

    @Override
    public void start(Stage stage)
        {
        if (!DataManipulation.SessionManipulation.userConnected())
            {
            Session.Session.getInstance().getLogin().start(Session.Session.getInstance().getStage());
            }
        stage.setTitle("Profile");
        stage.setScene(scene);
        stage.show();
        }

    }
