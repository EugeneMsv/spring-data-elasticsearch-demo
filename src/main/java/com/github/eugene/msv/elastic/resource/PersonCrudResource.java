package com.github.eugene.msv.elastic.resource;

import com.github.eugene.msv.elastic.document.Person;
import com.github.eugene.msv.elastic.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PersonCrudResource {

    private final PersonRepository personRepository;


    @PostMapping("/person")
    public Person create(@RequestBody Person person) {
        person.setUid(UUID.randomUUID().toString());
        final Person createdPerson = personRepository.save(person);
        log.info("Created person={}", person);
        return createdPerson;
    }

    @PutMapping("/person")
    public Person update(@RequestBody Person person) {
        Person foundPerson = Optional.ofNullable(person)
                .map(Person::getUid)
                .flatMap(personRepository::findById)
                .orElseThrow(() -> new IllegalArgumentException("Existing person not found"));
        updateExistingPerson(person, foundPerson);
        Person updatedPerson = personRepository.save(foundPerson);
        log.info("Updated person={}", updatedPerson);
        return updatedPerson;
    }


    @GetMapping("/person/{uid}")
    public Person find(@PathVariable("uid") String uid) {
        Person foundPerson = Optional.ofNullable(uid)
                .flatMap(personRepository::findById)
                .orElseThrow(() -> new IllegalArgumentException("Existing person not found"));
        log.info("Found person={}", foundPerson);
        return foundPerson;
    }


    @GetMapping("/person")
    public Page<Person> findPage(Pageable pageable) {
        Page<Person> page = personRepository.findAll(pageable);
        log.info("Found people page ={}", page);
        return page;
    }


    private void updateExistingPerson(Person person, Person existingPerson) {
        existingPerson.setBirthDate(person.getBirthDate());
        existingPerson.setAddresses(person.getAddresses());
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setSecondName(person.getSecondName());
    }


}
