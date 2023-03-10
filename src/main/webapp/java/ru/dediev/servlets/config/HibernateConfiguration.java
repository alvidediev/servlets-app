package ru.dediev.servlets.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {
    public static SessionFactory factory;

    private HibernateConfiguration() {
    }

    public static synchronized SessionFactory getSessionFactory() {
        if (factory == null) {
            factory = new Configuration().configure("hibernate.cfg.xml").
                    buildSessionFactory();
        }
        return factory;
    }
    public static synchronized Session getSession() {
        return getSessionFactory().openSession();
    }
}
