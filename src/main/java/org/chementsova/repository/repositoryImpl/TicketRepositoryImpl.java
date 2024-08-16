package org.chementsova.repository.repositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.chementsova.hibernate.HibernateSessionFactoryUtil;
import org.chementsova.model.Person;
import org.chementsova.model.Ticket;
import org.chementsova.model.TicketType;
import org.chementsova.repository.TicketRepository;

import java.util.List;

public class TicketRepositoryImpl implements TicketRepository {

    private static final String QUERY_GET_ALL = "SELECT t from Ticket t";

    private static final String QUERY_GET_BY_PERSON = "SELECT t from Ticket t where t.person.id = :person_id";

    private static final String QUERY_UPDATE_TICKET = "UPDATE Ticket t SET t.ticketType = :ticketType WHERE t.id = :ticketId";

    @Override
    public void save(Ticket ticket) {
        EntityManager entityManager = HibernateSessionFactoryUtil
                .getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(ticket);
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
    public List<Ticket> getAll() {
        EntityManager entityManager = HibernateSessionFactoryUtil
                .getEntityManagerFactory().createEntityManager();
        List<Ticket> tickets = null;
        try {
            tickets = entityManager.createQuery(QUERY_GET_ALL, Ticket.class).getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return tickets;
    }

    @Override
    public Ticket getByID(int ticketId) {
        EntityManager entityManager = HibernateSessionFactoryUtil
                .getEntityManagerFactory().createEntityManager();
        Ticket ticket = null;
        try {
            ticket = entityManager.find(Ticket.class, ticketId);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return ticket;
    }

    @Override
    public List<Ticket> getByPerson(Person person) {
        EntityManager entityManager = HibernateSessionFactoryUtil
                .getEntityManagerFactory().createEntityManager();
        List<Ticket> tickets = null;
        try {
            tickets = entityManager.createQuery(QUERY_GET_BY_PERSON, Ticket.class)
                    .setParameter("person_id",person.getId()).getResultList();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return tickets;
    }

    @Override
    public void update(Ticket ticket, TicketType ticketType) {
        EntityManager entityManager = HibernateSessionFactoryUtil
                .getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.createQuery(QUERY_UPDATE_TICKET).setParameter("ticketType",ticketType)
                    .setParameter("ticketId", ticket.getId()).executeUpdate();
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
