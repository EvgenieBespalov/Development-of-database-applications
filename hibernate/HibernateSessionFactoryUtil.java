package hibernate;

import models.Listtags;
import models.Notebook;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;


public class HibernateSessionFactoryUtil
{
    private static SessionFactory sessionFactory; //настройка и работа с сессиями (фабрика сессий)

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Listtags.class);
                configuration.addAnnotatedClass(Notebook.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Что-то не так! " + e);
            }
        }
        return sessionFactory;
    }
}