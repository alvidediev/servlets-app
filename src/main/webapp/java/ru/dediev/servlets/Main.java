package ru.dediev.servlets;

import org.hibernate.Session;
import ru.dediev.servlets.config.HibernateConfiguration;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateConfiguration.getSession();

    }
}
