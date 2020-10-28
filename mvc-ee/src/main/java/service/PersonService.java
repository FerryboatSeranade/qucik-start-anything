package service;

import entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> findPersons(Person person);
}
