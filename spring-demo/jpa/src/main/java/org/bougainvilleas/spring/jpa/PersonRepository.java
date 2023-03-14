package org.bougainvilleas.spring.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person,Long> {
    List<Person> findByLastName(String lastName);
    Person findById(long id);
}
