/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManipulation;

import Controllers.CellViewController;


import static DataManipulation.FriendshipsManipulation.AcceptFriendships;
import static DataManipulation.FriendshipsManipulation.addFriendship;
import static DataManipulation.FriendshipsManipulation.endFriendships;
import static DataManipulation.UserManipulation.getPhoto;
import static DataManipulation.UserManipulation.localAcceptFriendship;
import static DataManipulation.UserManipulation.localDeleteFriendship;
import static DataManipulation.UserManipulation.localaddFriendship;

import Controllers.FriendListController;
import static DataManipulation.ConversationManipulation.getLastElement;
import Model.Friend;
import Model.Friendship;
import Model.Message;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

/**
 * @author Elkamel
 */
public class CellViewManipulation
    {
    FriendshipsManipulation FM = new FriendshipsManipulation();

    private void makeDeleteButton(JFXButton button, Friend friend, Parent model, ObservableList<Parent> observableProp, JFXListView MyTable)
        {
        button.setOnAction((event) ->
        {
       
        endFriendships(friend.getFriendshipId());
        Friendship f = FriendSearchManipulation.getFriendship(Session.Session.getInstance().getConnectedUser(), friend.getFriend());
        if (button.getText().equals("Delete friend"))
            {
            FM.sendFriendship(f, Session.Session.getInstance().getConectiontoserver(), "Del");
            } else
            {
            FM.sendFriendship(f, Session.Session.getInstance().getConectiontoserver(), "Dec");
            }
        localDeleteFriendship(f.getId(), Session.Session.getInstance().getConnectedUser());
        observableProp.remove(model);
        MyTable.setItems(FXCollections.observableArrayList(observableProp));
        });
        }

    private void makeAcceptButton(JFXButton button, Friend friend, Parent model, ObservableList<Parent> observableProp, JFXListView MyTable)
        {
        button.setOnAction((event) ->
        {
        Friendship f = AcceptFriendships(friend.getFriendshipId());
        localAcceptFriendship(friend.getFriendshipId(), Session.Session.getInstance().getConnectedUser());
        FM.sendFriendship(f, Session.Session.getInstance().getConectiontoserver(), "Acc");
        observableProp.remove(model);
        friend.setFriendshipStatue("Friends");
        observableProp.add(createCellView(friend, observableProp, MyTable));
        MyTable.setItems(FXCollections.observableArrayList(observableProp));
        });
        }

    private void makeNotFriendButton(JFXButton button, Friend friend, Parent model, ObservableList<Parent> observableProp, JFXListView MyTable)
        {
        button.setOnAction((event) ->
        {
        Friendship f = addFriendship(Session.Session.getInstance().getConnectedUser(), friend.getFriend());
        int id = localaddFriendship(f, Session.Session.getInstance().getConnectedUser());
        FM.sendFriendship(FriendSearchManipulation.getFriendship(Session.Session.getInstance().getConnectedUser(), friend.getFriend()), Session.Session.getInstance().getConectiontoserver(), "Add");
        observableProp.remove(model);
        friend.setFriendshipStatue("RequestWaiting");
        friend.setFriendshipId(id);
        observableProp.add(createCellView(friend, observableProp, MyTable));
        MyTable.setItems(FXCollections.observableArrayList(observableProp));
        });
        }

    private void setCellViewButtons(Controllers.CellViewController model_controller, Friend friend, Parent model, ObservableList<Parent> observableProp, JFXListView MyTable)
        {
        if (friend.getFriendshipStatue().equals("Friends"))
            {
            model_controller.DeclineButton.setVisible(false);
            model_controller.DeclineButton.setManaged(false);
            model_controller.friendButton.setText("Delete friend");
            makeDeleteButton(model_controller.friendButton, friend, model, observableProp, MyTable);
            }
        if (friend.getFriendshipStatue().equals("Request"))
            {
            model_controller.friendButton.setText("Accept friend");
            model_controller.DeclineButton.setText("Decline friend");
            makeDeleteButton(model_controller.DeclineButton, friend, model, observableProp, MyTable);
            makeAcceptButton(model_controller.friendButton, friend, model, observableProp, MyTable);
            }
        if (friend.getFriendshipStatue().equals("RequestWaiting"))
            {
            model_controller.friendButton.setText("Delete Request");
            model_controller.DeclineButton.setVisible(false);
            model_controller.DeclineButton.setManaged(false);
            makeDeleteButton(model_controller.friendButton, friend, model, observableProp, MyTable);
            }
        if (friend.getFriendshipStatue().equals("NotFriends"))
            {
            model_controller.friendButton.setText("Add Friend");
            model_controller.DeclineButton.setVisible(false);
            model_controller.DeclineButton.setManaged(false);
            makeNotFriendButton(model_controller.friendButton, friend, model, observableProp, MyTable);
            }
        }

    private static void setCellViewInfo(Controllers.CellViewController model_controller, Friend friend)
        {
        model_controller.bioField.setText(friend.getFriend().getBio());
        model_controller.usernameField.setText(friend.getFriend().getUsername());
        model_controller.nameField.setText(friend.getFriend().getFirstName() + " " + friend.getFriend().getLastName());
        model_controller.emailField.setText(friend.getFriend().getEmail());
        model_controller.image.setImage(getPhoto(friend.getFriend()));
        }

    public Parent createCellView(Friend friend, ObservableList<Parent> observableProp, JFXListView MyTable)
        {
        Parent model = null;
        try
            {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/cellView.fxml"));
            model = (Parent) fxmlLoader.load();
            Controllers.CellViewController model_controller = fxmlLoader.<Controllers.CellViewController>getController();
            setCellViewInfo(model_controller, friend);
            setCellViewButtons(model_controller, friend, model, observableProp, MyTable);
            } catch (IOException ex)
            {
            Logger.getLogger(FriendListController.class.getName()).log(Level.SEVERE, null, ex);
            }
        return model;
        }

    public Parent createCellView2(Model.Conversation conv)
        {
        Parent model = null;
        try
            {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmls/cellView2.fxml"));
            model = (Parent) fxmlLoader.load();
            Controllers.CellView2Controller model_controller = fxmlLoader.<Controllers.CellView2Controller>getController();
            manageItems(model_controller, model, conv);
            manageClick(model, conv, model_controller);
            } catch (IOException ex)
            {
            Logger.getLogger(CellViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        return model;
        }

    private void manageItems(Controllers.CellView2Controller model_controller, Parent model, Model.Conversation conv)
        {
        model_controller.conversationNameField.setText(conv.getName());
        if (conv.getMessageCollection() != null && !conv.getMessageCollection().isEmpty())
            {
            model_controller.lastMessageField.setText(this.convertLongMessage((getLastElement(conv.getMessageCollection())).getText()));
            } else
            {
            model_controller.lastMessageField.setText("say hi");
            }
        Model.User reciver;
        if (conv.getPerson1().getId() == Session.Session.getInstance().getConnectedUser().getId())
            reciver = conv.getPerson2();
        else reciver = conv.getPerson1();
        model_controller.usernameField.setText(reciver.getUsername());
        {
        model_controller.image.setImage(getPhoto(reciver));
        }
        }

    private void manageClick(Parent model, Model.Conversation conv, Controllers.CellView2Controller model_controller)
        {
        model.setOnMousePressed((MouseEvent event) ->
        {
        Session.Session.getInstance().getConversationController().setSelectedConversationId(conv.getId());
        String Text;
        Text = "";
        if (conv.getMessageCollection() == null)
            {
            conv.setMessageCollection(new ArrayList<Model.Message>());
            }
        for (Message msg : conv.getMessageCollection())
            {
            Text += msg.getSender().getUsername() + " : " + msg.getText() + "\n";
            }
        Session.Session.getInstance().getConversationController().TextMessage.setText(Text);
        Session.Session.getInstance().getConversationController().TextMessage.appendText("");
        });
        }

    public static String convertLongMessage(String message)
        {
        String lastmessage = "";
        int count = 0;
        String[] words = message.split(" ");
        for (String word : words)
            {
            if (count < 24)
                {
                if (lastmessage.length() + word.length() < 24)
                    {
                    count += word.length();
                    lastmessage += " " + word;
                    } else
                    {
                    lastmessage += " ...";
                    break;
                    }
                } else break;
            }
        if (lastmessage.equals(" ..."))
            {
            lastmessage = message.substring(0, Math.min(message.length(), 24));
            lastmessage += " ...";
            }
        lastmessage.trim();
       
        return lastmessage;
        }
    }
