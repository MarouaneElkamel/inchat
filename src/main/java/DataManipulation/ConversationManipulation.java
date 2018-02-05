/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManipulation;

import Hibernate.Connection;
import Model.User;
import Model.Conversation;
import com.ui.connectionToServer;
import java.util.Iterator;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Elkamel
 */
public class ConversationManipulation
    {
    public static Conversation getConversation(Model.User user1, Model.User user2)
        {
        Session session = Connection.getInstance().getFactory().openSession();
        Transaction tx = null;
        List list = null;
        try
            {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Conversation where (person1 = :id and person2 = :idd) or (person1 = :idd and person2 = :id)");
            query.setParameter("id", user1.getId());
            query.setParameter("idd", user2.getId());
            list = query.list();
            tx.commit();
            } catch (HibernateException e)
            {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            } finally
            {
            session.close();
            }
        if (list.size() > 0) return (Conversation) list.get(0);
        return null;
        }

    public static Model.Conversation getConversationLocal(User user, Integer id)
        {
        for (Model.Conversation conv : user.getConversationCollection())
            {
            if (conv.getId().equals(id)) return conv;
            }
        for (Model.Conversation conv : user.getConversationCollection1())
            {
            if (conv.getId().equals(id)) return conv;
            }
        return null;
        }

    public static Model.Conversation addConversation(User user1, User user2)
        {
        Model.Conversation newconv = new Model.Conversation();
        newconv.setPerson1(user1);
        newconv.setPerson2(user2);
        newconv.setName(user1.getUsername());
        Session session = Connection.getInstance().getFactory().openSession();
        Transaction tx = null;
        Integer UserID = null;
        try
            {
            tx = session.beginTransaction();
            session.save(newconv);
            tx.commit();
            } catch (HibernateException e)
            {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            } finally
            {
            session.close();
            }
        return newconv;
        }

    public static void sendConversation(Model.Conversation conv, connectionToServer conectiontoserver)
        {
        Model.User reciver = null;
        if (conv.getPerson1().getUsername().equals(conectiontoserver.username)) reciver = conv.getPerson2();
        else reciver = conv.getPerson1();
        if (conectiontoserver.model.contains(reciver.getUsername()))
            {
            conectiontoserver.sendToTarget(conv, reciver.getUsername());
            }
        }

    public static Conversation updateConversationName(String name, Conversation conv)
        {
        conv.setName(name);
        Session session = Connection.getInstance().getFactory().openSession();
        Transaction tx = null;
        try
            {
            tx = session.beginTransaction();
            session.update(conv);
            tx.commit();
            } catch (HibernateException e)
            {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            } finally
            {
            session.close();
            }
        return conv;
        }

    public static void localupdateconv(User user, int id, Conversation conv)
        {
        getConversationLocal(user, id).setName(conv.getName());
        }
    
        public static <T> T getLastElement(final Iterable<T> elements)
        {
        if (elements != null)
            {
            final Iterator<T> itr = elements.iterator();
            T lastElement = itr.next();
            while (itr.hasNext())
                {
                lastElement = itr.next();
                }
            return lastElement;
            }
        return null;
        }
    }
