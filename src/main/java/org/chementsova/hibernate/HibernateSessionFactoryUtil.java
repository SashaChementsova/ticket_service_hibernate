package org.chementsova.hibernate;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HibernateSessionFactoryUtil {
    private static EntityManagerFactory entityManagerFactory;


    public static EntityManagerFactory getEntityManagerFactory() {
        if(entityManagerFactory == null) {
            entityManagerFactory = Persistence
                    .createEntityManagerFactory("hibernate_properties");
        }
        return entityManagerFactory;
    }
}
