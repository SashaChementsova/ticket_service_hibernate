package org.chementsova.service;

import org.chementsova.model.Person;
import org.chementsova.model.Ticket;
import org.chementsova.model.TicketType;

import java.util.List;

public interface TicketService {
    void saveTicket(Ticket ticket);

    List<Ticket> getTickets();

    Ticket getTicketByID(int ticketId);

    List<Ticket> getTicketsByPerson(Person person);

    void updateTicketType(Ticket ticket, TicketType ticketType);
}
