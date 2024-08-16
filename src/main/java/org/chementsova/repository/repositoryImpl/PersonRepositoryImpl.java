package org.chementsova.repository.repositoryImpl;

import jakarta.persistence.*;
import org.chementsova.hibernate.HibernateSessionFactoryUtil;
import org.chementsova.model.Person;
import org.chementsova.model.TicketType;
import org.chementsova.repository.PersonRepository;

import java.util.List;

public class PersonRepositoryImpl implements PersonRepository {

    private static final String QUERY_GET_ALL = "SELECT p from Person p";

    private static final String QUERY_UPDATE_PERSON = "UPDATE Person p SET p.name = :name WHERE p.id = :personId";

    private static final String QUERY_UPDATE_TICKET_TYPE = "UPDATE Ticket t SET t.ticketType = :ticketType WHERE t.person.id = :personId";

    @Override
    public  void save(Person person) {
        EntityManager entityManager = HibernateSessionFactoryUtil
                .getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(person);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            exception.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Person> getAll() {
        EntityManager entityManager = HibernateSessionFactoryUtil
                .getEntityManagerFactory().createEntityManager();
        List<Person> people = null;
        try {
            people = entityManager.createQuery(QUERY_GET_ALL, Person.class).getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return people;
    }

    @Override
    public Person getByID(int personId) {
        EntityManager entityManager = HibernateSessionFactoryUtil
                .getEntityManagerFactory().createEntityManager();
        Person person = null;
        try {
            person = entityManager.find(Person.class, personId);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return person;
    }

    @Override
    public void delete(Person person) {
        EntityManager entityManager = HibernateSessionFactoryUtil
                .getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            person = entityManager.merge(person);
            entityManager.remove(person);
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void updateBoth(Person person, String name, TicketType ticketType) {
        EntityManager entityManager = HibernateSessionFactoryUtil
                .getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.createQuery(QUERY_UPDATE_PERSON)
                    .setParameter("name", name)
                    .setParameter("personId", person.getId()).executeUpdate();
            entityManager.createQuery(QUERY_UPDATE_TICKET_TYPE)
                    .setParameter("ticketType", ticketType)
                    .setParameter("personId", person.getId()).executeUpdate();
            transaction.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
