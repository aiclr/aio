package org.bougainvilleas.spring.graphqlserver;

import java.util.Arrays;
import java.util.List;

public class Author{
  String id;
  String firstName;
  String lastName;

  public Author(String id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String id(){
    return this.id;
  }

  private static List<Author> authors=Arrays.asList(
        new Author("author-1","Joshua","Bloch"),
        new Author("author-2","Douglas","Adams"),
        new Author("author-3","Bill","Bryson")
        );

public static Author getById(String id){
        return authors.stream()
        .filter(author-> author.id().equals(id))
        .findFirst()
        .orElse(null);
        }
        }