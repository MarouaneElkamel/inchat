/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManipulation;

import Hibernate.Connection;
import com.ui.connectionToServer;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 * @author Elkamel
 */
public class MessageManipulation
    {
    public static int MessageAdd(Model.Message msg)
        {
        org.hibernate.Session session = Connection.getInstance().getFactory().openSession();
        Transaction tx = null;
        Integer msgid = -1;
        try
            {
            tx = session.beginTransaction();
            msgid = (Integer) session.save(msg);
            tx.commit();
            } catch (HibernateException e)
            {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            } finally
            {
            session.close();
            }
        return msgid;
        }

    public void sendMessage(Model.Message msg, String id, connectionToServer conectiontoserver)
        {
        if (conectiontoserver.model.contains(msg.getReceiver().getUsername()))
            {
            conectiontoserver.sendToTarget(msg, id);
            }
        }
    }
