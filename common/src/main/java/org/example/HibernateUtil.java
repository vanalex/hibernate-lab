package org.example;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Arrays;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(Class<?>...clazz) {
        if (sessionFactory == null) {
            try {
                final Configuration hibernateConfig = HibernateConfigurationBuilder.buildConfig();
                Arrays.asList(clazz).forEach(hibernateConfig::addAnnotatedClass);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(hibernateConfig.getProperties()).build();
                sessionFactory = hibernateConfig.buildSessionFactory(serviceRegistry);
                return sessionFactory;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


}
