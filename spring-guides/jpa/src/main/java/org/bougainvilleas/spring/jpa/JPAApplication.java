package org.bougainvilleas.spring.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootApplication
public class JPAApplication {

    private static final Logger log = LoggerFactory.getLogger(JPAApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(JPAApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(PersonRepository repository) {
        return (args) -> {
            // save a few persons
            repository.save(new Person("Jack", "Bauer"));
            repository.save(new Person("Chloe", "O'Brian"));
            repository.save(new Person("Kim", "Bauer"));
            repository.save(new Person("David", "Palmer"));
            repository.save(new Person("Michelle", "Dessler"));

            // fetch all persons
            log.info("persons found with findAll():");
            log.info("-------------------------------");
            for (Person person : repository.findAll()) {
                log.info(person.toString());
            }

            // fetch an individual person by ID
            Person person = repository.findById(1L);
            log.info("person found with findById(1L):");
            log.info("--------------------------------");
            log.info(person.toString());

            // fetch persons by last name
            log.info("person found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));

            // fetch persons by last name
            log.info("person found with findAllByPage():");
            log.info("--------------------------------------------");
            Pageable pageable= PageRequest.of(0,2);
            Page<Person> personPage = repository.findAll(pageable);
            log.info("TotalPages={},TotalElements={}",personPage.getTotalPages(),personPage.getTotalElements());
            personPage.forEach(bauer -> log.info(bauer.toString()));

            // fetch persons by last name
            log.info("person found with findAllByPageHql():");
            log.info("--------------------------------------------");
            Page<Person> personPage1 = repository.findAllHql(pageable);
            log.info("TotalPages={},TotalElements={}",personPage1.getTotalPages(),personPage1.getTotalElements());
            personPage1.forEach(bauer -> log.info(bauer.toString()));

            // fetch persons by last name
            log.info("person found with findAllByPageSql():");
            log.info("--------------------------------------------");
            Page<Person> personPage2 = repository.findAllSql(pageable);
            log.info("TotalPages={},TotalElements={}",personPage2.getTotalPages(),personPage2.getTotalElements());
            personPage2.forEach(bauer -> log.info(bauer.toString()));

        };
    }
}
