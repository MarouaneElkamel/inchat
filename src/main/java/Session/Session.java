/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import starter.*;
import Controllers.*;
import static DataManipulation.SessionManipulation.connectUser;
import static DataManipulation.SessionManipulation.disconnectUser;
import com.ui.connectionToServer;
import javafx.stage.Stage;

/**
 * @author Elkamel
 */
public class Session
    {
    private boolean connected;
    private ConversationController conversationController = null;
    private FriendListController friendListController = null;
    private FriendSearchController friendSearchController = null;
    private LoginController loginController = null;
    private ProfileController profileController = null;
    private SignupController signUpController = null;
    private Conversation conversation = null;
    private FriendList friendList = null;
    private FriendSearch friendSearch = null;
    private Login login = null;
    private Profile profile = null;
    private Signup signUp = null;
    private static Model.User connectedUser = null;
    private static Session INSTANCE = new Session();
    private static Stage stage = null;
    private connectionToServer conectiontoserver = null;

    public static Session getInstance()
        {
        if (INSTANCE == null)
            {
            setINSTANCE(new Session());
            }
        return INSTANCE;
        }

    public Model.User getConnectedUser()
        {
        return connectedUser;
        }

    public void setConnectedUser(Model.User user)
        {
        String username = "";
        if (Session.connectedUser != null) username = Session.connectedUser.getUsername();
        connectedUser = user;
        if (connectedUser != null)
            {
            connectUser(user);
            } else
            {
            disconnectUser(username);
            }
        }

    public Stage getStage()
        {
        if (Session.stage == null) Session.stage = new Stage();
        return Session.stage;
        }

    public void setStage(Stage stagee)
        {
        stage = stagee;
        }

    public boolean isConnected()
        {
        return connected;
        }

    public void setConnected(boolean connected)
        {
        this.connected = connected;
        }

    public ConversationController getConversationController()
        {
        this.getConversation();
        return conversationController;
        }

    public void setConversationController(ConversationController conversationController)
        {
        this.conversationController = conversationController;
        }

    public FriendListController getFriendListController()
        {
        this.getFriendList();
        return friendListController;
        }

    public void setFriendListController(FriendListController friendListController)
        {
        this.friendListController = friendListController;
        }

    public FriendSearchController getFriendSearchController()
        {
        this.getFriendSearch();
        return friendSearchController;
        }

    public void setFriendSearchController(FriendSearchController friendSearchController)
        {
        this.friendSearchController = friendSearchController;
        }

    public LoginController getLoginController()
        {
        return loginController;
        }

    public void setLoginController(LoginController loginController)
        {
        this.loginController = loginController;
        }

    public ProfileController getProfileController()
        {
        this.getProfile();
        return profileController;
        }

    public void setProfileController(ProfileController profileController)
        {
        this.profileController = profileController;
        }

    public SignupController getSignUpController()
        {
        this.getSignUp();
        return signUpController;
        }

    public void setSignUpController(SignupController signUpController)
        {
        this.signUpController = signUpController;
        }

    private static void setINSTANCE(Session aINSTANCE)
        {
        INSTANCE = aINSTANCE;
        }

    public Conversation getConversation()
        {
        if (conversation == null)
            {
            conversation = new Conversation();
            }
        return conversation;
        }

    public void setConversation(Conversation conversation)
        {
        this.conversation = conversation;
        }

    public FriendList getFriendList()
        {
        if (friendList == null)
            {
            friendList = new FriendList();
            }
        return friendList;
        }

    public void setFriendList(FriendList friendList)
        {
        this.friendList = friendList;
        }

    public FriendSearch getFriendSearch()
        {
        if (friendSearch == null)
            {
            friendSearch = new FriendSearch();
            }
        return friendSearch;
        }

    public void setFriendSearch(FriendSearch friendSearch)
        {
        this.friendSearch = friendSearch;
        }

    public Login getLogin()
        {
        if (login == null)
            {
            login = new Login();
            }
        return login;
        }

    public void setLogin(Login login)
        {
        this.login = login;
        }

    public Profile getProfile()
        {
        if (profile == null)
            {
            profile = new Profile();
            }
        return profile;
        }

    public void setProfile(Profile profile)
        {
        this.profile = profile;
        }

    public Signup getSignUp()
        {
        if (signUp == null)
            {
            signUp = new Signup();
            }
        return signUp;
        }

    public void setSignUp(Signup signUp)
        {
        this.signUp = signUp;
        }

    public connectionToServer getConectiontoserver()
        {
        return conectiontoserver;
        }

    public void setConectiontoserver(connectionToServer conectiontoserver)
        {
        this.conectiontoserver = conectiontoserver;
        }

   
    }
