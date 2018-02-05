package starter;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controllers.SignupController;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Peggy Fisher
 */
public class Signup extends Application
    {
    public Parent root = null;
    public Scene scene = null;

    {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/Signup.fxml"));
    try
        {
        root = (Parent) fxmlLoader.load();
        } catch (IOException ex)
        {
        Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    Session.Session.getInstance().setSignUpController((SignupController) fxmlLoader.getController());
    scene = new Scene(root);
    }

    @Override
    public void start(Stage stage) 
        {
        stage.setTitle("Signup");
        stage.setScene(scene);
        stage.show();
        }
/**
 * @param args the command line arguments
 */
    }
