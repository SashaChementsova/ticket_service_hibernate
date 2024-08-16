package org.chementsova.repository;

import org.chementsova.model.Person;
import org.chementsova.model.Ticket;
import org.chementsova.model.TicketType;

import java.util.List;

public interface TicketRepository {
    void save(Ticket ticket);

    List<Ticket> getAll();

    Ticket getByID(int ticketId);

    List<Ticket> getByPerson(Person person);

    void update(Ticket ticket, TicketType ticketType);
}
