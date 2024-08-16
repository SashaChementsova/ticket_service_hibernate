package org.chementsova.service.serviceImpl;

import org.chementsova.model.Person;
import org.chementsova.model.Ticket;
import org.chementsova.model.TicketType;
import org.chementsova.repository.TicketRepository;
import org.chementsova.repository.repositoryImpl.TicketRepositoryImpl;
import org.chementsova.service.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl() {
        this.ticketRepository = new TicketRepositoryImpl();
    }

    @Override
    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.getAll();
    }

    @Override
    public Ticket getTicketByID(int ticketId) {
        return ticketRepository.getByID(ticketId);
    }

    @Override
    public List<Ticket> getTicketsByPerson(Person person) {
        return ticketRepository.getByPerson(person);
    }

    @Override
    public void updateTicketType(Ticket ticket, TicketType ticketType) {
        ticketRepository.update(ticket, ticketType);
    }
}
