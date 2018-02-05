package Controllers; /**
 * Created by Elkamel on 6/28/17.
 */

import DataManipulation.CellViewManipulation;

import static DataManipulation.FriendshipsManipulation.getFriends;
import static ControllerManipulation.Navigation.*;
import Model.Friend;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;

public class FriendListController implements Initializable
    {
    @FXML
    private JFXListView MyTable;
    ObservableList<Parent> observableProp = FXCollections.observableArrayList();
    DataManipulation.CellViewManipulation cellViewManipulation = new CellViewManipulation();

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
    void FriendSearchPressed(ActionEvent event) 
        {
        goFriendSearch();
        }

    @FXML
    void HelpPressed(ActionEvent event)
        {
        goAbout();
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

    @Override
    public void initialize(URL location, ResourceBundle resources)
        {
        this.refresh();
        Session.Session.getInstance().getStage().centerOnScreen();
        }

    public void refresh()
        {
        MyTable.getItems().clear();
        observableProp.clear();
        for (Friend friend : getFriends(Session.Session.getInstance().getConnectedUser()))
            {
            observableProp.add(cellViewManipulation.createCellView(friend, observableProp, MyTable));
            }
        MyTable.setItems(FXCollections.observableArrayList(observableProp));
        }
    }
