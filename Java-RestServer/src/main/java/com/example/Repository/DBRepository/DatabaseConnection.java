package com.example.Repository.DBRepository;

import com.example.Domain.Trial;
import com.example.Repository.Exceptions.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by andrei on 2017-05-07.
 */
public class DatabaseConnection {
    private static SessionFactory sessionFactory = null;

    public static Session newSession() throws RepositoryException {
        if (sessionFactory == null) establishConnection();
        return sessionFactory.openSession();
    }

    private static void establishConnection() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Trial.class)
                .buildSessionFactory();
    }

    public static void closeConnection(){
        if(sessionFactory == null)
            return;
        sessionFactory.close();
    }
}
