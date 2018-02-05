package Controllers; /**
 * Created by Elkamel on 6/28/17.
 */

import static ControllerManipulation.Navigation.*;
import DataManipulation.CellViewManipulation;

import static DataManipulation.FriendSearchManipulation.getFriendship;
import static DataManipulation.FriendSearchManipulation.getList;
import static DataManipulation.FriendSearchManipulation.getRelation;

import Model.Friend;
import Model.Friendship;
import Model.User;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class FriendSearchController implements Initializable
    {
    @FXML
    private TextField UsernameField;
    @FXML
    private TextField FirstNameField;
    @FXML
    private TextField LastNameField;
    @FXML
    private TextField EmailField;
    @FXML
    private JFXListView MyTable;
    
    DataManipulation.CellViewManipulation cellViewManipulation = new CellViewManipulation();
    ObservableList<Parent> observableProp = FXCollections.observableArrayList();

    @FXML
    void AboutPressed(ActionEvent event)
        {
        goAbout();
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
    void LogoutPressed(ActionEvent event)
        {
        goLogout();
        }

    @FXML
    void ProfilePressed(ActionEvent event)
        {
        goProfile();
        }

    @FXML
    void infoChanged(KeyEvent event)
        {
        if (event.getCode().equals(KeyCode.ENTER))
            {
            if (!UsernameField.getText().isEmpty() || !FirstNameField.getText().isEmpty() || !LastNameField.getText().isEmpty() || !EmailField.getText().isEmpty())
                {
                refresh();
                }
            }
        }

    @Override
    public void initialize(URL location, ResourceBundle resources)
        {
        Session.Session.getInstance().getStage().centerOnScreen();
        }

    public void refresh()
        {
        MyTable.getItems().clear();
        observableProp.clear();
        for (User user : getList(UsernameField.getText(), FirstNameField.getText(), LastNameField.getText(), EmailField.getText()))
            {
            Friend friend;
            Friendship friendship = getFriendship(Session.Session.getInstance().getConnectedUser(), user);
            if (friendship != null)
                {
                String statu = getRelation(friendship, Session.Session.getInstance().getConnectedUser());
                friend = new Friend(user, statu, friendship.getId());
                } else
                {
                friend = new Friend(user, "NotFriends", -1);
                }
            if (friend.getFriend().getId() != Session.Session.getInstance().getConnectedUser().getId())
                observableProp.add(cellViewManipulation.createCellView(friend, observableProp, MyTable));
            }
        MyTable.setItems(FXCollections.observableArrayList(observableProp));
        }
    }
