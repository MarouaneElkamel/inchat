package com.socket;


import static DataManipulation.ConversationManipulation.getConversationLocal;
import static DataManipulation.UserManipulation.localAcceptFriendship;
import static DataManipulation.UserManipulation.localDeleteFriendship;
import static DataManipulation.UserManipulation.localaddConversation;
import static DataManipulation.UserManipulation.localaddFriendship;
import Model.Friendship;
import com.ui.connectionToServer;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javafx.application.Platform;

import javax.swing.table.DefaultTableModel;

public class SocketClient1 implements Runnable{
    
    public int port;
    public String serverAddr;
    public Socket socket;
    public  connectionToServer con;
    public ObjectInputStream In;
    public ObjectOutputStream Out;
    public History hist;
    
    public SocketClient1(connectionToServer con) throws IOException{
        this.serverAddr = con.serverAddr; this.port = con.port;
        this.con = con;
        socket = new Socket(InetAddress.getByName(serverAddr), port);
            
        Out = new ObjectOutputStream(socket.getOutputStream());
        Out.flush();
        In = new ObjectInputStream(socket.getInputStream());
        
       
    }

    @Override
    public void run() {
        boolean keepRunning = true;
        while(keepRunning){
            try {
                Message msg = (Message) In.readObject();
                System.out.println("Incoming : "+msg.toString());
                
                if(msg.type.equals("message")){
                    if(msg.recipient.equals(con.username)){
                      handlemsg(msg);
                       
                      
                       System.out.println("["+msg.sender +" > Me] : " + msg.content + "\n");
                    }
                    else{
                        System.out.println("["+ msg.sender +" > "+ msg.recipient +"] : " + msg.content + "\n");
                    }
                                            
                    if(!msg.content.equals(".bye") && !msg.sender.equals(con.username)){
                        String msgTime = (new Date()).toString();
                        
                        try{
                            hist.addMessage(msg, msgTime);
                            DefaultTableModel table = (DefaultTableModel) con.historyFrame.jTable1.getModel();
                            table.addRow(new Object[]{msg.sender, msg.content, "Me", msgTime});
                        }
                        catch(Exception ex){}  
                    }
                }
                else if(msg.type.equals("login")){
                    if(msg.content.equals("TRUE")){
                     
                        System.out.println("[SERVER > Me] : Login Successful\n");
                      
                    }
                    else{
                       System.out.println("[SERVER > Me] : Login Failed\n");
                    }
                }
                else if(msg.type.equals("test")){
                    
                }
                else if(msg.type.equals("newuser")){
                    if(!msg.content.equals(con.username)){
                        boolean exists = false;
                        for(int i = 0; i < con.model.getSize(); i++){
                            if(con.model.getElementAt(i).equals(msg.content)){
                                exists = true; break;
                            }
                        }
                        if(!exists){ con.model.addElement(msg.content); }
                    }
                }
                else if(msg.type.equals("signup")){
                    if(msg.content.equals("TRUE")){
                        
                      System.out.println("[SERVER > Me] : Singup Successful\n");
                    }
                    else{
                        System.out.println("[SERVER > Me] : Signup Failed\n");
                    }
                }
                else if(msg.type.equals("signout")){
                    if(msg.content.equals(con.username)){
                        System.out.println("["+ msg.sender +" > Me] : Bye\n");
                        
                        
                        for(int i = 1; i < con.model.size(); i++){
                            con.model.removeElementAt(i);
                        }
                        
                        con.clientThread.stop();
                    }
                    else{
                        con.model.removeElement(msg.content);
                        System.out.println("["+ msg.sender +" > All] : "+ msg.content +" has signed out\n");
                    }
                }
//                else if(msg.type.equals("upload_req")){
//                    
//                    if(JOptionPane.showConfirmDialog(ui, ("Accept '"+msg.content+"' from "+msg.sender+" ?")) == 0){
//                        
//                        JFileChooser jf = new JFileChooser();
//                        jf.setSelectedFile(new File(msg.content));
//                        int returnVal = jf.showSaveDialog(ui);
//                       
//                        String saveTo = jf.getSelectedFile().getPath();
//                        if(saveTo != null && returnVal == JFileChooser.APPROVE_OPTION){
//                            Download dwn = new Download(saveTo, ui);
//                            Thread t = new Thread(dwn);
//                            t.start();
//                            //send(new Message("upload_res", (""+InetAddress.getLocalHost().getHostAddress()), (""+dwn.port), msg.sender));
//                            send(new Message("upload_res", ui.username, (""+dwn.port), msg.sender));
//                        }
//                        else{
//                            send(new Message("upload_res", ui.username, "NO", msg.sender));
//                        }
//                    }
//                    else{
//                        send(new Message("upload_res", ui.username, "NO", msg.sender));
//                    }
//                }
//                else if(msg.type.equals("upload_res")){
//                    if(!msg.content.equals("NO")){
//                        int port  = Integer.parseInt(msg.content);
//                        String addr = msg.sender;
//                        
//                        ui.jButton5.setEnabled(false); ui.jButton6.setEnabled(false);
//                        Upload upl = new Upload(addr, port, ui.file, ui);
//                        Thread t = new Thread(upl);
//                        t.start();
//                    }
//                    else{
//                        ui.jTextArea1.append("[SERVER > Me] : "+msg.sender+" rejected file request\n");
//                    }
//                }
//                else{
//                    ui.jTextArea1.append("[SERVER > Me] : Unknown message type\n");
//                }
            }
            catch(Exception ex) {
                keepRunning = false;
                 System.out.println("[Application > Me] : Connection Failure\n");
             
                for(int i = 1; i < con.model.size(); i++){
                   con.model.removeElementAt(i);
                }
                
                con.clientThread.stop();
                
                System.out.println("Exception SocketClient run()");
                ex.printStackTrace();
            }
        }
    }
    
    public void send(Message msg){
        try {
            Out.writeObject(msg);
            Out.flush();
            System.out.println("Outgoing : "+msg.toString());
            System.out.println(msg.getMsg());
            if(msg.type.equals("message") && !msg.content.equals(".bye")){
                String msgTime = (new Date()).toString();
                try{
                    hist.addMessage(msg, msgTime);               
                    DefaultTableModel table = (DefaultTableModel) con.historyFrame.jTable1.getModel();
                    table.addRow(new Object[]{"Me", msg.content, msg.recipient, msgTime});
                }
                catch(Exception ex){}
            }
        } 
        catch (IOException ex) {
            System.out.println("Exception SocketClient send()");
        }
    }
    
    public void closeThread(Thread t){
        t = null;
    }
    
    private void handlemsg(Message msg)
    {
        if (msg.getMsg() != null)
        { Model.Conversation conv = getConversationLocal(Session.Session.getInstance().getConnectedUser(), Integer.parseInt(msg.getConversationID()));
                      if (conv.getMessageCollection() == null)
                          conv.setMessageCollection(new ArrayList<Model.Message>());
                      conv.getMessageCollection().add(msg.getMsg());
                     Platform.runLater(() -> {Session.Session.getInstance().getConversationController().refreshText(conv);}); 
                
        }
        
        if(msg.getFriendship() != null)
        {
                 
                  System.out.println(msg.getFriendship().getId() +" - "+ msg.getFriendship().getFriend1() + " -> "+msg.getFriendship().getFriend2()+" : " +msg.getFriendship().getStatus());
            
            if (msg.getFriendshipModificationType().equals("Del"))
            {
              localDeleteFriendship(msg.getFriendship().getId(),Session.Session.getInstance().getConnectedUser());
               
            }
            if (msg.getFriendshipModificationType().equals("Add"))
            { 
                localaddFriendship(msg.getFriendship(),Session.Session.getInstance().getConnectedUser());
  
                
            }
            if (msg.getFriendshipModificationType().equals("Acc"))
            {
           
                
            localAcceptFriendship(msg.getFriendship().getId(),Session.Session.getInstance().getConnectedUser());
             
            }
             if (msg.getFriendshipModificationType().equals("Dec"))
            {
            localDeleteFriendship(msg.getFriendship().getId(),Session.Session.getInstance().getConnectedUser());
             
           
            }
            Platform.runLater(() -> {   Session.Session.getInstance().getFriendListController().refresh();
                                        Session.Session.getInstance().getFriendSearchController().refresh();
            });
        }
        
          if(msg.getConv() != null)
          {
              
              localaddConversation(msg.getConv(),Session.Session.getInstance().getConnectedUser());
              
                Platform.runLater(() -> {Session.Session.getInstance().getConversationController().addConv(msg.getConv());   
            });
          }
        
        
    }
    
    public static boolean exist(Friendship f,Model.User u)
    {
        Collection<Friendship> list = Session.Session.getInstance().getConnectedUser().getFriendshipCollection();
        list.addAll(Session.Session.getInstance().getConnectedUser().getFriendshipCollection1());
       for (Friendship c : list)
       {
           if (c.getId() == f.getId() )return true;
       }
        return false;
    }
    public static Collection<Friendship> remove (Collection<Friendship> list,Friendship f)
    {
         Collection<Friendship> listt = list;
         for (Friendship c : listt)
       {    System.out.println(c.getId());
           if (c.getId() == f.getId() )
           {
               list.remove(c);
              
               break;
           }
          
       }
         return list;
         
    }
}
