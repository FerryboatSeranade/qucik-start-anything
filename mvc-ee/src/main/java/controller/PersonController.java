package controller;

import entity.Person;
import exception.PersonException;
import service.PersonService;
import service.impl.PersonServiceImpl;

public class PersonController {
    public static void main(String[] args) {
        PersonService personService = new PersonServiceImpl();
        Person person = new Person();
        person.setId(1);
        try {
            personService.findPersons(person);
        }catch (PersonException pe){
            System.out.println("pe");
        }catch (Exception e){
            System.out.println("e ： "+e.getMessage());
        }
    }
}
