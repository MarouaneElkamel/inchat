/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerManipulation;

import static DataManipulation.UserManipulation.saveAvatar;
import static DataManipulation.Verification.get_SHA_512_SecurePassword;
import static DataManipulation.Verification.isStrongPassword;
import static DataManipulation.Verification.verifyBirthDate;
import static DataManipulation.Verification.verifyConfirmPassword;
import static DataManipulation.Verification.verifyEmail;
import static DataManipulation.Verification.verifyGender;
import static DataManipulation.Verification.verifyName;
import static DataManipulation.Verification.verifyPhone;
import static DataManipulation.Verification.verifyUsername;
import Model.Avatar;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Elkamel
 */
public class Validation
{
 public static boolean fileOkay(File fileChoosen )   
 {
    return (fileChoosen != null && fileChoosen.canRead() && fileChoosen.isFile() && fileChoosen.exists());
 }
 
 public static void createAvatar(File fileChoosen,Model.User newUser)
 {
    newUser.setPhoto(fileChoosen.getAbsolutePath());
    Avatar avatar = saveAvatar(fileChoosen.getAbsolutePath());
    newUser.setAvatarid(avatar);
 }
 
 public static void avatarUpdate(File fileChoosen,Model.User newUser)
 {
      if (fileOkay(fileChoosen))
            {
            createAvatar(fileChoosen,newUser);
            }
 }
 
 public static int usernameUpdate(TextField UsernameField ,Model.User newUser)
 {
     if (verifyUsername(UsernameField.getText()))
            {
            newUser.setUsername(UsernameField.getText());
            } else
            {
            UsernameField.clear();
            UsernameField.setPromptText("invalid Username");
            return 1;
            }
     return 0;
 }
 public static int firstNameUpdate(TextField NameField ,Model.User newUser)
 { 
   
      if (verifyName(NameField.getText()))
            {
            newUser.setFirstName(NameField.getText());
            } else
            {
         
            NameField.clear();
            NameField.setPromptText("invalid Name");
            return 1;
            }
      return 0;
 }
  public static int lastNameUpdate(TextField NameField ,Model.User newUser)
 { 
   
      if (verifyName(NameField.getText()))
            {
            newUser.setLastName(NameField.getText());
            } else
            {
         
           NameField.clear();
            NameField.setPromptText("invalid Name");
            return 1;
            }
      return 0;
 }
 
 public static int passwordUpdate(PasswordField PasswordField,PasswordField ConfirmPasswordField,Model.User newUser)
 {
       if (verifyConfirmPassword(PasswordField.getText(), ConfirmPasswordField.getText()))
            {
            if (isStrongPassword(PasswordField.getText()))
                {
                try
                {
                    newUser.setPassword(get_SHA_512_SecurePassword(PasswordField.getText(), "elkamel"));
                }
                catch (UnsupportedEncodingException ex)
                {
                    Logger.getLogger(Validation.class.getName()).log(Level.SEVERE, null, ex);
                }
                } else
                {
                
                PasswordField.clear();
                ConfirmPasswordField.clear();
                PasswordField.setPromptText("weak password");
                return 1;
                }
            } else
            {
         
            ConfirmPasswordField.clear();
            ConfirmPasswordField.setPromptText("password didn't match");
              return 1;
            }
       return 0;
 }
 public static int genderUpdate(ChoiceBox<?> GenderChooser,Model.User newUser)
 {
      if (verifyGender((String) GenderChooser.getValue()))
            {
            newUser.setGender((String) GenderChooser.getValue());
            } else
            {
           
            GenderChooser.getSelectionModel().selectFirst();
            return 1;
            }
      return 0;
 }
 public static int telephoneUpdate(TextField TelephoneField,Model.User newUser)
 {
      if (verifyPhone(TelephoneField.getText()))
            {
            newUser.setTelephone(TelephoneField.getText());
            } else
            {    
            TelephoneField.clear();
            TelephoneField.setPromptText("invalid phone number");
            return 1;
            }
      return 0;
 }
 
 public static int birthdayUpdate(DatePicker BirthdayChooser,Model.User newUser)
 {
      if (BirthdayChooser.getValue() != null)
            {
            if (verifyBirthDate(BirthdayChooser.getValue()))
                {
                newUser.setBirthDate(BirthdayChooser.getValue());
                } else
                {
               
                BirthdayChooser.setValue(null);
               return 1; }
            } else
            {
            
            BirthdayChooser.setValue(null);
            return 1;}
      return 0;
 }
 
 public static int emailUpdate(TextField EmailField,Model.User newUser)
 {
     if (verifyEmail(EmailField.getText()))
            {
            newUser.setEmail(EmailField.getText());
            } else
            {
            
            EmailField.clear();
            EmailField.setPromptText("invalid email address");
            return 1;
            }
 return 0;}
 
}
