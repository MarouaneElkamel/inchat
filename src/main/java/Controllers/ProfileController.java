package Controllers;


import static ControllerManipulation.Navigation.*;
import static ControllerManipulation.Validation.*;
import static DataManipulation.UserManipulation.getPhoto;
import static DataManipulation.UserManipulation.updateUser;
import static DataManipulation.Verification.get_SHA_512_SecurePassword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ProfileController implements Initializable
    {
    File fileChoosen;
    @FXML
    private ImageView myImage;
    @FXML
    private TextField UsernameField;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField LastNameField;
    @FXML
    private TextField EmailField;
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
    private Text successMessage;

    @FXML
    void AboutPressed(ActionEvent event)
        {
        goAbout();
        }

    @FXML
    void ChooseFilePressed(ActionEvent event)
        {
        fileChoosen = goSelectAvatar();
        }

    @FXML
    void ClosePressed(ActionEvent event)
        {
        goClose();
        }

    @FXML
    void ConversationPressed(ActionEvent event)
        {
        goConversation();
        }

    @FXML
    void FriendListPressed(ActionEvent event)
        {
        goFriendList();
        }

    @FXML
    void FriendSearchPressed(ActionEvent event)
        {
        goFriendSearch();
        }

    @FXML
    void LogoutPressed(ActionEvent event)
        {
        goLogout();
        }

    @FXML
    void SignInPressed(ActionEvent event) throws UnsupportedEncodingException, Exception
        {
        int errors = 0;
        int changes = 0;
        successMessage.setText("");
            if (fileOkay(fileChoosen)){
            if (!Session.Session.getInstance().getConnectedUser().getPhoto().equals(fileChoosen.getAbsolutePath()))
                {
                changes++;
                 avatarUpdate(fileChoosen,Session.Session.getInstance().getConnectedUser());
                }
            }
            if (!Session.Session.getInstance().getConnectedUser().getFirstName().equals(FirstNameField.getText()))
                {
                changes++;
               errors+=firstNameUpdate(FirstNameField,Session.Session.getInstance().getConnectedUser());
            }
       
            if (!Session.Session.getInstance().getConnectedUser().getLastName().equals(LastNameField.getText()))
                {
                changes++;
                 errors+=lastNameUpdate(LastNameField,Session.Session.getInstance().getConnectedUser());
            }
        if (!PasswordField.getText().equals("zaazazza"))
            {
           
                    if (!Session.Session.getInstance().getConnectedUser().getPassword().equals(get_SHA_512_SecurePassword(PasswordField.getText(), "elkamel")))
                        {
                        changes++;
                         errors += passwordUpdate(PasswordField,ConfirmPasswordField,Session.Session.getInstance().getConnectedUser());
                }
            }
      
            if (!GenderChooser.getValue().equals(Session.Session.getInstance().getConnectedUser().getGender()))
                {
                changes++;
                errors += genderUpdate(GenderChooser,Session.Session.getInstance().getConnectedUser());
            }
       
            if (!Session.Session.getInstance().getConnectedUser().getTelephone().equals(TelephoneField.getText()))
                {
                changes++;
                errors += telephoneUpdate(TelephoneField,Session.Session.getInstance().getConnectedUser());
            }
        if (BirthdayChooser.getValue() != null)
            {
         
                if (!BirthdayChooser.getValue().equals(Session.Session.getInstance().getConnectedUser().getBirthDate()))
                    {
                    changes++;
                    errors += birthdayUpdate(BirthdayChooser,Session.Session.getInstance().getConnectedUser());
            }
            }
      
            if (!EmailField.getText().equals(Session.Session.getInstance().getConnectedUser().getEmail()))
                {
                changes++;
                errors += emailUpdate(EmailField,Session.Session.getInstance().getConnectedUser());
            }
        if (!BioField.getText().equals(Session.Session.getInstance().getConnectedUser().getBio()))
            {
            changes++;
            Session.Session.getInstance().getConnectedUser().setBio(BioField.getText());
            }
      
        if (errors == 0 && changes > 0)
            {
            updateUser(Session.Session.getInstance().getConnectedUser());
            successMessage.setText("Update Done");
              reset();
            }
        }

    @Override
    public void initialize(URL location, ResourceBundle resources)
        {
      reset();
        }
    public void reset()
    {
        this.myImage.setImage(getPhoto(Session.Session.getInstance().getConnectedUser()));
        UsernameField.setText(Session.Session.getInstance().getConnectedUser().getUsername());
        FirstNameField.setText(Session.Session.getInstance().getConnectedUser().getFirstName());
        LastNameField.setText(Session.Session.getInstance().getConnectedUser().getLastName());
        EmailField.setText(Session.Session.getInstance().getConnectedUser().getEmail());
        PasswordField.setText("zaazazza");
        ConfirmPasswordField.setText("zaazazza");
        String Gender = Session.Session.getInstance().getConnectedUser().getGender();
        if (Gender.endsWith("Male")) GenderChooser.getSelectionModel().selectFirst();
        else GenderChooser.getSelectionModel().selectLast();
        TelephoneField.setText(Session.Session.getInstance().getConnectedUser().getTelephone());
        BioField.setText(Session.Session.getInstance().getConnectedUser().getBio());
        BirthdayChooser.setValue(Session.Session.getInstance().getConnectedUser().getBirthDate());
        Session.Session.getInstance().getStage().centerOnScreen();  
    }
    
    }
