/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManipulation;

import static DataManipulation.ConfigManipulation.getConfig;
import Model.Config;
import Model.User;
import Session.Session;
import com.socket.Message;
import com.ui.connectionToServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elkamel
 */
public class SessionManipulation
    {
    public static boolean userConnected()
        {
        if (Session.getInstance().getConnectedUser() == null) return false;
        return true;
        }

    public static int connectUser(User user)
        {
            Config u;
            u = getConfig();
            Session.getInstance().setConectiontoserver(new connectionToServer());
           Session.getInstance().getConectiontoserver().connectToServer(u);
           Session.getInstance().getConectiontoserver().signuptoServer(user.getUsername(), user.getPassword());
           Session.getInstance().getConectiontoserver().LogIntoServer(user.getUsername(), user.getPassword());
            
        user.getConversationCollection();
        user.getConversationCollection1();
        List<Model.Friendship> col = new ArrayList();
        List<Model.Friendship> col1 = new ArrayList();
        if (user.getFriendshipCollection() != null && !user.getFriendshipCollection().isEmpty())
            {
            col.addAll(user.getFriendshipCollection());
            }
        if (user.getFriendshipCollection1() != null && !user.getFriendshipCollection1().isEmpty())
            {
            col.addAll(user.getFriendshipCollection1());
            }
        user.getMessageCollection();
        user.getMessageCollection1();
        
        
        return 1;
        }
    
    
    public static void disconnectUser(String username)
    {
        try
                {
                Session.getInstance().getConectiontoserver().client.send(new Message("message", username, ".bye", "SERVER"));
                Session.getInstance().getConectiontoserver().clientThread.stop();
                } catch (Exception ex)
                {
                }
             Session.getInstance().setConversation(null);
           Session.getInstance().setConectiontoserver(null);
           Session.getInstance().setConnected(false);
           Session.getInstance().setConversationController(null);
           Session.getInstance().setFriendList(null);
            Session.getInstance().setFriendListController(null);
            Session.getInstance().setFriendSearch(null);
           Session.getInstance().setFriendSearchController(null);
           Session.getInstance().setProfile(null);
           Session.getInstance().setProfileController(null);
    }
    }
