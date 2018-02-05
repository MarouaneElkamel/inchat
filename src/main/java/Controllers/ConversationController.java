package Controllers;
/**
 * Created by Elkamel on 6/28/17.
 */

import static ControllerManipulation.Navigation.*;
import DataManipulation.CellViewManipulation;
import DataManipulation.ConversationManipulation;
import static DataManipulation.ConversationManipulation.addConversation;
import static DataManipulation.ConversationManipulation.getConversationLocal;
import static DataManipulation.ConversationManipulation.localupdateconv;
import static DataManipulation.ConversationManipulation.sendConversation;
import DataManipulation.MessageManipulation;
import static DataManipulation.MessageManipulation.MessageAdd;
import static DataManipulation.UserManipulation.getUserByUsername;
import static DataManipulation.UserManipulation.localaddConversation;
import Model.Friendship;
import Model.Message;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ConversationController implements Initializable
    {
    ObservableList<Parent> observableProp = FXCollections.observableArrayList();
    private Integer selectedConversationId = -1;

    @FXML
    void LoginPressed(ActionEvent event)
        {
       
        }

    DataManipulation.CellViewManipulation cellViewManipulation = new CellViewManipulation();
    DataManipulation.MessageManipulation messageManipulation = new MessageManipulation();
    @FXML
    private JFXListView MyTable;
    @FXML
    public TextArea TextMessage;
    @FXML
    private TextArea Message;
    @FXML
    private Button SendPressed;

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
    void ProfilePressed(ActionEvent event)
        {
       goProfile();
        }

    @FXML
    void NamePressed(ActionEvent event)
        {
        TextInputDialog dialog = new TextInputDialog("Conversation Name");
        dialog.setTitle("Conversation Name");
        dialog.setHeaderText("Conversation Nameg");
        dialog.setContentText("Please enter your name:");
// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
            {
            Model.Conversation conv = ConversationManipulation.updateConversationName(result.get(), getConversationLocal(Session.Session.getInstance().getConnectedUser(), this.selectedConversationId));
            localupdateconv(Session.Session.getInstance().getConnectedUser(), this.selectedConversationId, conv);
            }

        }

    @FXML
    void NickNamesPressed(ActionEvent event)
        {
        }

    @FXML
    void ColorPressed(ActionEvent event)
        {
        }

    @FXML
    void EmojiPressed(ActionEvent event)
        {
        }

    @Override
    public void initialize(URL location, ResourceBundle resources)
        {
        Session.Session.getInstance().getStage().centerOnScreen();
        automateScrolldown();
        textAreasManagment();
        refresh();
        if (!this.observableProp.isEmpty())
            {
            String Text;
            Text = "";
            Collection<Message> listt = null;
            if (this.getSelectedConversationId() > 0)
                listt = getConversationLocal(Session.Session.getInstance().getConnectedUser(), getSelectedConversationId()).getMessageCollection();
            else
                {
                if (!Session.Session.getInstance().getConnectedUser().getConversationCollection().isEmpty())
                    {
                    Model.Conversation conv1 = (Model.Conversation) (Session.Session.getInstance().getConnectedUser().getConversationCollection().toArray())[0];
                    listt = conv1.getMessageCollection();
                    this.setSelectedConversationId(conv1.getId());
                    for (Message msg : listt)
                        {
                        Text += msg.getSender().getUsername() + " : " + msg.getText() + "\n";
                        }
                    this.TextMessage.setText(Text);
                    }
                }
            TextMessage.appendText("");
            }
        }

    @FXML
    void sendButtonPressed(ActionEvent event) throws IOException
        {
            if (!this.Message.getText().trim().isEmpty())
            {Model.Conversation conv = getConversationLocal(Session.Session.getInstance().getConnectedUser(), this.getSelectedConversationId());
        Message newmsg = new Message();
        newmsg.setText(this.Message.getText().trim());
        newmsg.setDateSent(new Date());
        newmsg.setSender(Session.Session.getInstance().getConnectedUser());
      
        if (Session.Session.getInstance().getConnectedUser().getId().equals(conv.getPerson1().getId()))
            newmsg.setReceiver(conv.getPerson2());
        else newmsg.setReceiver(conv.getPerson1());
        newmsg.setConversation(conv);
        if (conv.getMessageCollection() == null)
            {
            conv.setMessageCollection(new ArrayList<Model.Message>());
            }
        conv.getMessageCollection().add(newmsg);
        MessageAdd(newmsg);
        messageManipulation.sendMessage(newmsg, conv.getId().toString(), Session.Session.getInstance().getConectiontoserver());
        refreshText(conv);
     
            }    this.Message.clear();}

    public void refreshText(Model.Conversation conv)
        {
        Session.Session.getInstance().getConversationController().setSelectedConversationId(conv.getId());
        String Text;
        Text = "";
        for (Message msg : conv.getMessageCollection())
            {
            Text += msg.getSender().getUsername() + " : " + msg.getText() + "\n";
            }
        Session.Session.getInstance().getConversationController().TextMessage.setText(Text);
        TextMessage.appendText("");
        }

    public void addConv(Model.Conversation conv)
        {
        if (conv != null)
            {
            observableProp.add(cellViewManipulation.createCellView2(conv));
            MyTable.setItems(FXCollections.observableArrayList(observableProp));
            }
        }

    public void refresh()
        {
        MyTable.getItems().clear();
        observableProp.clear();
        Collection<Model.Conversation> list = Session.Session.getInstance().getConnectedUser().getConversationCollection();
        list.addAll(Session.Session.getInstance().getConnectedUser().getConversationCollection1());
        if (!list.isEmpty())
            {
            for (Model.Conversation conv : list)
                {
                observableProp.add(cellViewManipulation.createCellView2(conv));
                }
            MyTable.setItems(FXCollections.observableArrayList(observableProp));
            }
        }



    /**
     * @return the selectedConversationId
     */
    public Integer getSelectedConversationId()
        {
        return selectedConversationId;
        }

    /**
     * @param selectedConversationId the selectedConversationId to set
     */
    public void setSelectedConversationId(Integer selectedConversationId)
        {
        this.selectedConversationId = selectedConversationId;
        }

    @FXML
    void startNewConv(ActionEvent event)
        {
        List<String> choices = new ArrayList<>();
        Collection<Model.Conversation> convList = Session.Session.getInstance().getConnectedUser().getConversationCollection();
        convList.addAll(Session.Session.getInstance().getConnectedUser().getConversationCollection1());
        for (Friendship friendship : Session.Session.getInstance().getConnectedUser().getFriendshipCollection())
            {
            boolean test;
            if (friendship.getStatus().equals("Friends"))
                {
                test = false;
                for (Model.Conversation conv : convList)
                    {
                    if ((conv.getPerson1().getId() == Session.Session.getInstance().getConnectedUser().getId() && conv.getPerson2().getId() == friendship.getFriend2().getId()) || (conv.getPerson2().getId() == Session.Session.getInstance().getConnectedUser().getId() && conv.getPerson1().getId() == friendship.getFriend2().getId()))
                        {
                        test = true;
                        }
                    }
                if (test == false) choices.add(friendship.getFriend2().getUsername());
                }
            }
        for (Friendship friendship : Session.Session.getInstance().getConnectedUser().getFriendshipCollection1())
            {
            boolean test;
            if (friendship.getStatus().equals("Friends"))
                {
                test = false;
                for (Model.Conversation conv : convList)
                    {
                    if ((conv.getPerson1().getId() == Session.Session.getInstance().getConnectedUser().getId() && conv.getPerson2().getId() == friendship.getFriend1().getId()) || (conv.getPerson2().getId() == Session.Session.getInstance().getConnectedUser().getId() && conv.getPerson1().getId() == friendship.getFriend1().getId()))
                        {
                        test = true;
                        }
                    }
                if (test == false) choices.add(friendship.getFriend1().getUsername());
                }
            }
        Collections.sort(choices);
        ChoiceDialog<String> dialog;
        if (choices.size() == 0) dialog = new ChoiceDialog<>("you have no friends", choices);
        else dialog = new ChoiceDialog<>(choices.get(0), choices);
        dialog.setTitle("Friend chooser");
        dialog.setHeaderText("who do you wanna text !");
        dialog.setContentText("Choose your Firend:");
// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        Model.User reciver;
        Model.Conversation conv1;
        if (result.isPresent())
            {
            if (!result.get().equals("you have no friends"))
                {
                reciver = getUserByUsername(result.get());
                conv1 = addConversation(Session.Session.getInstance().getConnectedUser(), reciver);
                localaddConversation(conv1, Session.Session.getInstance().getConnectedUser());
                sendConversation(conv1, Session.Session.getInstance().getConectiontoserver());
                this.selectedConversationId = conv1.getId();
                this.addConv(conv1);
                }
           
            }
        }

    public void setText()
        {
        String text = "";
        text += Session.Session.getInstance().getConnectedUser();
        text += "\n";
        text += "Convlist";
        text += "\n";
        for (Model.Friendship f : Session.Session.getInstance().getConnectedUser().getFriendshipCollection())
            {
            text += f.getId() + " - " + f.getFriend1() + " -> " + f.getFriend2() + " : " + f.getStatus();
            text += "\n";
            }
        for (Model.Friendship f : Session.Session.getInstance().getConnectedUser().getFriendshipCollection1())
            {
            text += f.getId() + " - " + f.getFriend1() + " -> " + f.getFriend2() + " : " + f.getStatus();
            text += "\n";
            }
        this.TextMessage.setText(text);
        }

    public void automateScrolldown()
        {
        this.TextMessage.textProperty().addListener(new ChangeListener<Object>()
            {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue, Object newValue)
                {
                TextMessage.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
                //use Double.MIN_VALUE to scroll to the top
                }
            });
        }

    public void textAreasManagment()
        {
        this.TextMessage.setEditable(false);
        this.TextMessage.setWrapText(true);
        this.Message.setWrapText(true);
        }

    @FXML
    void keypressed(KeyEvent event)
        {
        if (event.getCode().equals(KeyCode.ENTER))
            {
            this.SendPressed.fire();
            }
        }
    }

