/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManipulation;

import Hibernate.Connection;
import Model.Friend;
import Model.Friendship;
import Model.User;
import com.ui.connectionToServer;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Elkamel
 */
public class FriendshipsManipulation
    {
    public static ArrayList<Model.Friend> getFriends(Model.User user)
        {
        ArrayList<Model.Friend> friends = new ArrayList();
        for (Model.Friendship friendship : user.getFriendshipCollection())
            {
            if (friendship.getStatus().equals("Request"))
                friends.add(new Friend(friendship.getFriend2(), "RequestWaiting", friendship.getId()));
            else friends.add(new Friend(friendship.getFriend2(), friendship.getStatus(), friendship.getId()));
            }
        for (Model.Friendship friendship : user.getFriendshipCollection1())
            {
            friends.add(new Friend(friendship.getFriend1(), friendship.getStatus(), friendship.getId()));
            }
        return friends;
        }

    public static Friendship endFriendships(int id)
        {
        Friendship u = null;
        Session session = Connection.getInstance().getFactory().openSession();
        Transaction tx = null;
        Integer UserID = null;
        try
            {
            tx = session.beginTransaction();
            u = (Friendship) session.load(Friendship.class, id);
            session.delete(u);
            tx.commit();
            } catch (HibernateException e)
            {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            } finally
            {
            session.close();
            }
        return u;
        }

    public static Friendship AcceptFriendships(int id)
        {
        Session session = Connection.getInstance().getFactory().openSession();
        Transaction tx = null;
        Integer UserID = null;
        Friendship u = null;
        try
            {
            tx = session.beginTransaction();
            u = (Friendship) session.load(Friendship.class, id);
            u.setStatus("Friends");
            session.update(u);
            tx.commit();
            } catch (HibernateException e)
            {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            } finally
            {
            session.close();
            }
        return u;
        }

    public static Friendship addFriendship(User user1, User user2)
        {
        Friendship newFriendship = FriendSearchManipulation.getFriendship(user1, user2);
        if (newFriendship == null)
            {
            newFriendship = new Friendship();
            newFriendship.setFriend1(user1);
            newFriendship.setFriend2(user2);
            newFriendship.setDate(new Date());
            newFriendship.setStatus("Request");
            Session session = Connection.getInstance().getFactory().openSession();
            Transaction tx = null;
            Integer UserID = null;
            try
                {
                tx = session.beginTransaction();
                session.save(newFriendship);
                tx.commit();
                } catch (HibernateException e)
                {
                if (tx != null) tx.rollback();
                e.printStackTrace();
                } finally
                {
                session.close();
                }
            }
        return newFriendship;
        }

    public void sendFriendship(Model.Friendship friendship, connectionToServer conectiontoserver, String statu)
        {
        Model.User reciver = null;
        if (friendship.getFriend1().getUsername().equals(conectiontoserver.username)) reciver = friendship.getFriend2();
        else reciver = friendship.getFriend1();
        if (conectiontoserver.model.contains(reciver.getUsername()))
            {
            conectiontoserver.sendToTarget(friendship, reciver.getUsername(), statu);
            }
        }
    }
