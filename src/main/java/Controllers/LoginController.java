package Controllers; /**
 * Created by Elkamel on 6/28/17.
 */

import static ControllerManipulation.Navigation.goLogin;
import static ControllerManipulation.Navigation.goSignin;
import static DataManipulation.UserManipulation.getUserByUsername;
import static DataManipulation.UserManipulation.verifyUserConnection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class LoginController implements Initializable
    {
    @FXML
    private Button loginButton;
    @FXML
    private Button SigninButton;
    @FXML
    private TextField usernameField;
    @FXML
    private Text errorText;
    @FXML
    private PasswordField passwordField;

    @FXML
    void LoginPressed(ActionEvent event) throws Exception
        {
        boolean test;
        Model.User user = getUserByUsername(usernameField.getText());
        test = verifyUserConnection(user, passwordField.getText());
        if (!test) errorText.setText("Please verify your informations");
        else
            {
           goLogin(user);
            }
        }

    @FXML
    void SigninPressed(ActionEvent event) 
        {
        goSignin();
        }

    @Override
    public void initialize(URL location, ResourceBundle resources)
        {
        }

    @FXML
    void keypressed(KeyEvent event)
        {
        if (event.getCode().equals(KeyCode.ENTER))
            {
            this.loginButton.fire();
            }
        }
    }
