/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManipulation;

import Hibernate.Connection;
import Model.Config;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 * @author Elkamel
 */
public class ConfigManipulation
    {
    public static Config getConfig()
        {
        org.hibernate.Session session = Connection.getInstance().getFactory().openSession();
        Transaction tx = null;
        Integer msgid = -1;
        Config u = null;
        try
            {
            tx = session.beginTransaction();
            u = (Config) session.load(Config.class, 1);
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
    }
