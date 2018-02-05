package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class CellView2Controller implements Initializable
    {
    @FXML
    public ImageView image;
    @FXML
    public Text usernameField;
    @FXML
    public Text conversationNameField;
    @FXML
    public Text lastMessageField;

    @Override
    public void initialize(URL url, ResourceBundle rb)
        {
        }
    }