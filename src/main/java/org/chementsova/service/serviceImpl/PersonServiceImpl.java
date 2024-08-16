package org.chementsova.service.serviceImpl;

import org.chementsova.model.Person;
import org.chementsova.model.TicketType;
import org.chementsova.repository.PersonRepository;
import org.chementsova.repository.repositoryImpl.PersonRepositoryImpl;
import org.chementsova.service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl() {
        this.personRepository = new PersonRepositoryImpl();
    }

    @Override
    public void savePerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public List<Person> getPeople() {
        return personRepository.getAll();
    }

    @Override
    public Person getPersonByID(int personId) {
        return personRepository.getByID(personId);
    }

    @Override
    public void deletePerson(Person person) {
        personRepository.delete(person);
    }

    @Override
    public void updatePersonAndTicket(Person person, String name, TicketType newTicketType) {
        personRepository.updateBoth(person, name, newTicketType);
    }
}
