package org.bougainvilleas.spring.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
  List<Person> findByLastName(String lastName);

  Person findById(long id);

  Page<Person> findAll(Pageable pageable);

  @Query("select p from Person p")
  Page<Person> findAllHql(Pageable pageable);

  /**
   * nativeQuery = true 执行原生SQL
   */
  @Query(value = "select * from person",
          countQuery = "select count(id) from person",
          nativeQuery = true)
  Page<Person> findAllSql(Pageable pageable);
}
