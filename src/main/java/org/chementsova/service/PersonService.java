package org.chementsova.service;

import org.chementsova.model.Person;
import org.chementsova.model.TicketType;

import java.util.List;

public interface PersonService {
    void savePerson(Person person);

    List<Person> getPeople();

    Person getPersonByID(int personId);

    void deletePerson(Person person);

    void updatePersonAndTicket(Person person, String name, TicketType newTicketType);
}
