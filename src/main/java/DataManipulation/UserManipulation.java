/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManipulation;

import static DataManipulation.Verification.get_SHA_512_SecurePassword;

import Hibernate.Connection;
import Model.Avatar;
import Model.Friendship;
import Model.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.image.Image;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * @author Elkamel
 */
public class UserManipulation
    {
    private static int employeeID;

    public static int UserAdd(Model.User U)
        {
        Session session = Connection.getInstance().getFactory().openSession();
        Transaction tx = null;
        
        User user = null;
        try
            {
            tx = session.beginTransaction();

              Query query = session.getNamedQuery("User.findByUsername");
              query.setParameter("username", U.getUsername());
              List<User> list =  query.getResultList();
              if(!list.isEmpty()) user = list.get(0);
               
            
            if (user == null) employeeID = (Integer) session.save(U);
            tx.commit();
            } catch (HibernateException e)
            {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            } finally
            {
            session.close();
            }
        if (user != null) return -1;
        return employeeID;
        }

    public static int updateUser(Model.User U)
        {
        Session session = Connection.getInstance().getFactory().openSession();
        Transaction tx = null;
        Integer UserID = null;
        User user = null;
        try
            {
            tx = session.beginTransaction();
            session.update(U);
            tx.commit();
            } catch (HibernateException e)
            {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            } finally
            {
            session.close();
            }
        return employeeID;
        }

    public static boolean verifyUserConnection(Model.User user, String passwrod) throws UnsupportedEncodingException
        {
        String hashedPassword = get_SHA_512_SecurePassword(passwrod, "elkamel");
        boolean test = false;
        if (user == null) test = false;
        else if (user.getPassword().equals(hashedPassword)) test = true;
        return test;
        }

    public static Model.User getUserByUsername(String username)
        {
        Session session = Hibernate.Connection.factory.openSession();
        Model.User user = null;
        try
            {
               Query query = session.getNamedQuery("User.findByUsername");
              query.setParameter("username", username);
              user = (User) query.getSingleResult();
           
            } catch (HibernateException e)
            {
            e.printStackTrace();
            } finally
            {
            session.close();
            }
        return user;
        }

    public static int localDeleteFriendship(int FriendshipId, Model.User user)
        {
        for (Friendship f : user.getFriendshipCollection())
            {
            if (f.getId().equals(FriendshipId))
                {
                user.getFriendshipCollection().remove(f);
                return 1;
                }
            }
        for (Friendship f : user.getFriendshipCollection1())
            {
            if (f.getId().equals(FriendshipId))
                {
                user.getFriendshipCollection1().remove(f);
                return 1;
                }
            }
        return 0;
        }

    public static int localAcceptFriendship(int FriendshipId, Model.User user)
        {
        for (Friendship f : user.getFriendshipCollection())
            {
            if (f.getId().equals(FriendshipId))
                {
                f.setStatus("Friends");
                return 1;
                }
            }
        for (Friendship f : user.getFriendshipCollection1())
            {
            if (f.getId().equals(FriendshipId))
                {
                f.setStatus("Friends");
                return 1;
                }
            }
        return 0;
        }

    public static int localaddFriendship(Friendship f, Model.User user)
        {
        List<Model.Friendship> col = new ArrayList();
        List<Model.Friendship> col1 = new ArrayList();
        if (user.getFriendshipCollection() != null && !user.getFriendshipCollection().isEmpty())
            {
            col.addAll(user.getFriendshipCollection());
            }
        if (user.getFriendshipCollection1() != null && !user.getFriendshipCollection1().isEmpty())
            {
            col1.addAll(user.getFriendshipCollection1());
            }
        if (f.getFriend1().getId().equals(user.getId())) col.add(f);
        else col1.add(f);
        user.setFriendshipCollection(col);
        user.setFriendshipCollection1(col1);
        return f.getId();
        }

    public static void localaddConversation(Model.Conversation conv, Model.User user)
        {
        if (conv.getPerson1().getId().equals(user.getId())) user.getConversationCollection().add(conv);
        else user.getConversationCollection1().add(conv);
        }

    public static Model.Avatar saveAvatar(String path)
        {
        File file = new File(path);
        byte[] bFile = new byte[(int) file.length()];
        try
            {
            FileInputStream fileInputStream = new FileInputStream(file);
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();
            } catch (Exception e)
            {
            e.printStackTrace();
            }
        Avatar avatar = new Avatar();
        avatar.setImage(bFile);
        avatar.setImageName(file.getName());
        Session session = Connection.getInstance().getFactory().openSession();
        Transaction tx = null;
        Integer UserID = null;
        User user = null;
        try
            {
            tx = session.beginTransaction();
            session.save(avatar);
            tx.commit();
            } catch (HibernateException e)
            {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            } finally
            {
            session.close();
            }
        return avatar;
        }

    public static Model.Avatar loadAvatar(int id)
        {
        Avatar avatar2 = null;
        Session session = Connection.getInstance().getFactory().openSession();
        Transaction tx = null;
        try
            {
            tx = session.beginTransaction();
            avatar2 = (Avatar) session.get(Avatar.class, id);
            if (avatar2 != null)
                {
                byte[] bAvatar = avatar2.getImage();
                try
                    {
                    FileOutputStream fos = new FileOutputStream(avatar2.getImageName());
                    fos.write(bAvatar);
                    fos.close();
                    } catch (Exception e)
                    {
                    e.printStackTrace();
                    }
                }
            tx.commit();
            } catch (HibernateException e)
            {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            } finally
            {
            session.close();
            }
        return avatar2;
        }

    public static Image getPhoto(Model.User user)
        {
        if (user.getAvatarid() == null)
            {
            return new Image("/resources/test.png");
            } else
            {
            File file = new File(user.getAvatarid().getImageName());
            if (file.exists())
                {
                try
                    {
                    return new Image(file.toURI().toURL().toString());
                    } catch (MalformedURLException ex)
                    {
                    Logger.getLogger(UserManipulation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else
                {
                loadAvatar(user.getAvatarid().getAvatarid());
                return getPhoto(user);
                }
            }
        return null;
        }
    }
