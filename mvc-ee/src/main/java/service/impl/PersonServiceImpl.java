package service.impl;

import entity.Person;
import exception.PersonException;
import service.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    @Override
    public List<Person> findPersons(Person person) {
        try {
            if(person.getId().equals(1)){
                throw new PersonException(1,"try内throw被catch");
            }
            System.out.println("1");
        } catch (PersonException e) {
            System.out.println("2");
            e.printStackTrace();
            System.out.println("3");
//            throw new Exception("catch内throw不能随意定义需要try-catch嵌套处理或者在method signature中抛给caller,编译都无法通过,要被注释");
            throw new RuntimeException("unchecked exception 可以 抛给上层");
/*
            try {
                throw new Exception("zzz");
            } catch (Exception exception) {
                System.out.println("嵌套的");
            }
*/
        } catch (Exception e){
            System.out.println("4");
            e.printStackTrace();
            System.out.println("5");
        }
        System.out.println("6");
        return null;
    }
}
