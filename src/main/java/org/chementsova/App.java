package org.chementsova;

import org.chementsova.model.Person;
import org.chementsova.model.Ticket;
import org.chementsova.model.TicketType;
import org.chementsova.service.PersonService;
import org.chementsova.service.TicketService;
import org.chementsova.service.serviceImpl.PersonServiceImpl;
import org.chementsova.service.serviceImpl.TicketServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        TicketService ticketService = new TicketServiceImpl();

        PersonService personService = new PersonServiceImpl();

        //personService.savePerson(new Person("Sasha", "2024-08-14"));

        Person person = personService.getPersonByID(3);

        //ticketService.saveTicket(new Ticket(TicketType.EVENT,"2024-08-14",person));
        //ticketService.saveTicket(new Ticket(TicketType.BUS,"2024-08-14",person));
        //ticketService.saveTicket(new Ticket(TicketType.PLANE,"2024-08-14",person));

        for(Person person1 : personService.getPeople()) {
            System.out.println(person1.getName());
        }

        //personService.updatePersonAndTicket(person, "Dasha", TicketType.TRAIN);

        //personService.deletePerson(personService.getPersonByID(2));

        ticketService.updateTicketType(ticketService.getTicketByID(1), TicketType.TRAIN);
    }
}
