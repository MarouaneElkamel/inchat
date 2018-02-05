/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Elkamel
 */
public class CellViewController implements Initializable
    {
    @FXML
    public Text usernameField;
    @FXML
    public Text nameField;
    @FXML
    public Text bioField;
    @FXML
    public HBox horizentalBox;
    @FXML
    public JFXButton DeclineButton;
    @FXML
    public Text emailField;
    @FXML
    public JFXButton friendButton;
    @FXML
    public ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
        {
        }
    }
