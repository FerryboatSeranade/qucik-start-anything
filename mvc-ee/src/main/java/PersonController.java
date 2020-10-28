import entity.Person;
import service.PersonService;
import service.impl.PersonServiceImpl;

public class PersonController {
    public static void main(String[] args) {
        PersonService personService = new PersonServiceImpl();
        Person person = new Person();
        person.setId(1);
        personService.findPersons(person);
    }
}
