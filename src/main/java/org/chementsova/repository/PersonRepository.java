package org.chementsova.repository;

import org.chementsova.model.Person;
import org.chementsova.model.TicketType;

import java.util.List;

public interface PersonRepository {

    void save(Person person);

    List<Person> getAll();

    Person getByID(int personId);

    void delete(Person person);

    void updateBoth(Person person, String name, TicketType newTicketType);

}
