/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManipulation;

import Hibernate.Connection;
import Model.Friendship;
import Model.User;

import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * @author Elkamel
 */
public class FriendSearchManipulation
    {
    public static ArrayList<Model.User> getList(String username, String firstName, String lastName, String email)
        {
        ArrayList<Model.User> founds = new ArrayList();
        if (!username.isEmpty() || !firstName.isEmpty() || !lastName.isEmpty() || !email.isEmpty())
            {
            Session session = Connection.getInstance().getFactory().openSession();
            Transaction tx = null;
            Integer UserID = null;
            try
                {
                tx = session.beginTransaction();
                 CriteriaBuilder builder = session.getSessionFactory().getCriteriaBuilder();
                 CriteriaQuery<User> criteria = builder.createQuery(User.class);
                 Root<User> personRoot = criteria.from(User.class);
              
                criteria.where(
                        builder.or(
                        builder.like(personRoot.get("username"),username),
                        builder.like(personRoot.get("firstName"),firstName),
                        builder.like(personRoot.get("lastName"),lastName),
                        builder.like(personRoot.get("email"),email)
                ));
                TypedQuery<User> q = session.getSessionFactory().createEntityManager().createQuery(criteria);
            founds = (ArrayList<User>) q.getResultList();
             

           
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
        return founds;
        }

    public static String getRelation(Friendship founds, User user1)
        {
        if (founds == null) return "NotFriends";
        if (founds.getFriend1().getId().equals(user1.getId()) && founds.getStatus().equals("Request"))
            return "RequestWaiting";
        return founds.getStatus();
        }

    public static Friendship getFriendship(User user1, User user2)
        {
        Friendship founds = null;
        for (Friendship f : user1.getFriendshipCollection())
            {
            if (Objects.equals(f.getFriend2().getId(), user2.getId())) return f;
            }
        for (Friendship f : user1.getFriendshipCollection1())
            {
            if (Objects.equals(f.getFriend1().getId(), user2.getId())) return f;
            }
        return founds;
//        
        }
    }
