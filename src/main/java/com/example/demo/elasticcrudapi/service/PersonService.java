package hh.elasticsearch.service;

import hh.elasticsearch.entity.Person;
import hh.elasticsearch.entity.Product;
import hh.elasticsearch.repo.PersonRepo;
import hh.elasticsearch.repo.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepo personRepo;
    public Iterable<Person> getAll() {
        return personRepo.findAll();
    }

    public Person addPerson(Person person) {
        return personRepo.save(person);
    }

    public void deletePerson(int id) {
        personRepo.deleteById(id);
    }


}
