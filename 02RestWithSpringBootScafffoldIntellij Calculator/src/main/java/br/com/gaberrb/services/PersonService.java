package br.com.gaberrb.services;

import br.com.gaberrb.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    public Person findById(String id){
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirtName("Gaber");
        person.setLastName("Rios");
        person.setAddress("Rua lala");
        person.setGender("Masculino");
        return person;
    }

    public List<Person> findAll(){

        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 7; i++) {
            Person person = mockPerson(i);
            persons.add(person);
            
        }
        return persons;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirtName("Person name" + i);
        person.setLastName("Last Name" + i);
        person.setAddress("Address" + i);
        person.setGender("Gender" + i);
        return person;
    }
}
