/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui;

import Model.Friendship;
import com.socket.History;
import com.socket.Message;
import com.socket.SocketClient1;
import java.io.File;
import javax.swing.DefaultListModel;

/**
 *
 * @author Elkamel
 */
public class connectionToServer
{        public SocketClient1 client;
    public int port;
    public String serverAddr, username, password;
    public Thread clientThread;
    public DefaultListModel model;
    public File file;
    public String historyFile = "D:/History.xml";
    public HistoryFrame historyFrame;
    public History hist;
    
    
        public void connectToServer(Model.Config u)
    {   model = new DefaultListModel();
            model.addElement("All");
          this.serverAddr = u.getAdresse();
            this.port = u.getPort();
        
            try{
                client = new SocketClient1(this);
                clientThread = new Thread(client);
                clientThread.start();
                client.send(new Message("test", "testUser", "testContent", "SERVER"));
            }
            catch(Exception ex){
               System.out.println("[Application > Me] : Server not found\n");
            }
    }
        public void sendToTarget(String msg,String target)
    {
          if(!msg.isEmpty() && !target.isEmpty()){
           
            client.send(new Message("message", username, msg, target));
        }
    }
        
            public void sendToTarget(Model.Message msg,String id)
    {
          Message msgtosend = new Message("message", username, msg.getText(), msg.getReceiver().getUsername());
          msgtosend.setConversationID(id);
          msgtosend.setMsg(msg);
          System.out.println(msg+" from send to target");
            client.send(msgtosend);
        
    }
    public void signuptoServer(String username,String password)
    {   this.username=username;
        this.password=password;
        if(!username.isEmpty() && !password.isEmpty()){
            client.send(new Message("signup", username, password, "SERVER"));
        }
    }
    
    public void LogIntoServer(String username,String password)
    {   this.username=username;
        this.password=password;
    if(!username.isEmpty() && !password.isEmpty()){
            client.send(new Message("login", username, password, "SERVER"));
        }
    }

    public void sendToTarget(Friendship friendship,String reviver,String statu)
    {
          Message msgtosend = new Message("message", username, "friendship", reviver);
          msgtosend.setFriendship(friendship);
          msgtosend.setFriendshipModificationType(statu);
          
            client.send(msgtosend); 
    }
       
     public void sendToTarget(Model.Conversation conv,String reviver)
    {
          Message msgtosend = new Message("message", username, "conversation", reviver);
          msgtosend.setConv(conv);
       
          
            client.send(msgtosend); 
    }
}
