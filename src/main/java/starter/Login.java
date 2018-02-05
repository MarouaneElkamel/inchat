package starter;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controllers.LoginController;
import Hibernate.Connection;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author Peggy Fisher
 */
public class Login extends Application
    {
    public Parent root = null;
    public Scene scene = null;

    {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/Login.fxml"));
    try
        {
        root = (Parent) fxmlLoader.load();
        } catch (IOException ex)
        {
        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    Session.Session.getInstance().setLoginController((LoginController) fxmlLoader.getController());
    Session.Session.getInstance().setLogin(this);
    scene = new Scene(root);
    }

    @Override
    public void start(Stage stage)
        {
        Session.Session.getInstance().setStage(stage);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.addEventHandler(WindowEvent.WINDOW_SHOWN, new EventHandler<WindowEvent>()
            {
            @Override
            public void handle(WindowEvent window)
                {
                Session.Session.getInstance().getStage().centerOnScreen();
                }
            });
        stage.setOnCloseRequest(e ->
        {
        System.exit(0);
        });
        stage.show();
        }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
        {
        Connection.getInstance();
        launch(args);
        }
    }
