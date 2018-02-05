package Controllers; /**
 * Created by Elkamel on 6/28/17.
 */

import static ControllerManipulation.Navigation.goBack;
import static ControllerManipulation.Navigation.goSelectAvatar;
import static ControllerManipulation.Validation.avatarUpdate;
import static ControllerManipulation.Validation.birthdayUpdate;
import static ControllerManipulation.Validation.emailUpdate;
import static ControllerManipulation.Validation.firstNameUpdate;
import static ControllerManipulation.Validation.genderUpdate;
import static ControllerManipulation.Validation.lastNameUpdate;

import static ControllerManipulation.Validation.passwordUpdate;
import static ControllerManipulation.Validation.telephoneUpdate;
import static ControllerManipulation.Validation.usernameUpdate;
import static DataManipulation.UserManipulation.UserAdd;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class SignupController implements Initializable
    {
    File fileChoosen;
    @FXML
    private TextField UsernameField;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField LastNameField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private PasswordField ConfirmPasswordField;
    @FXML
    private ChoiceBox<?> GenderChooser;
    @FXML
    private TextField TelephoneField;
    @FXML
    private DatePicker BirthdayChooser;
    @FXML
    private TextArea BioField;
    @FXML
    private TextField EmailField;

    @FXML
    void ChooseFilePressed(ActionEvent event)
        {
        
        fileChoosen = goSelectAvatar();
       
        }

    @FXML
    void SignInPressed(ActionEvent event) throws UnsupportedEncodingException, Exception
        {
        int errors = 0;
        Model.User newUser = new User();
        avatarUpdate(fileChoosen,newUser);
        errors += usernameUpdate(UsernameField,newUser);
        errors += firstNameUpdate(FirstNameField,newUser);
        errors += lastNameUpdate(LastNameField,newUser);
        errors += passwordUpdate(PasswordField,ConfirmPasswordField,newUser);
        errors += genderUpdate(GenderChooser,newUser);
        errors += telephoneUpdate(TelephoneField,newUser);
        errors += birthdayUpdate(BirthdayChooser,newUser);
        errors += emailUpdate(EmailField,newUser);
        newUser.setBio(BioField.getText());
        newUser.setLastLoginDate(new Date());
        if (errors == 0 && UserAdd(newUser) != -1)
            {
           goBack();
            } else if (errors == 0)
            {
            UsernameField.clear();
            UsernameField.setPromptText("username already exists");
            }
        }

    @FXML
    void ClearButtonPressed(ActionEvent event) throws Exception
        {
        UsernameField.clear();
        FirstNameField.clear();
        LastNameField.clear();
        PasswordField.clear();
        ConfirmPasswordField.clear();
        GenderChooser.getSelectionModel().selectFirst();
        TelephoneField.clear();
        BirthdayChooser.setValue(null);
        BioField.clear();
        EmailField.clear();
        }

    @FXML
    void BackButtonPressed(ActionEvent event) throws Exception
        {
        goBack();
        }

    @Override
    public void initialize(URL url, ResourceBundle rb)
        {
        }
    }

