/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Producer
 */
public class Connection
    {
    private static Connection connection;
    public static SessionFactory factory;
    private static ServiceRegistry registry;
    private static Scanner in = new Scanner(System.in);

    public static Connection getInstance()
        {
        if (connection == null)
            {
            connection = new Connection();
            }
        return connection;
        }

    static
        {
        try
            {
            factory = new Configuration().configure().buildSessionFactory();
            Session session = Hibernate.Connection.factory.openSession();
            session.close();
            } catch (Throwable ex)
            {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
            }
        }

    public SessionFactory getFactory()
        {
        return factory;
        }

    }