package org.bougainvilleas.spring.graphqlserver;

import java.util.Arrays;
import java.util.List;

public class Book{

  String id;
  String name;
  int pageCount;
  String authorId;

  public Book(String id, String name, int pageCount, String authorId) {
    this.id = id;
    this.name = name;
    this.pageCount = pageCount;
    this.authorId = authorId;
  }

  public String id(){
    return this.id;
  }
  public String authorId() {
    return this.authorId;
  }


private static List<Book> books=Arrays.asList(
        new Book("book-1","Effective Java",416,"author-1"),
        new Book("book-2","Hitchhiker's Guide to the Galaxy",208,"author-2"),
        new Book("book-3","Down Under",436,"author-3")
        );

public static Book getById(String id){
        return books.stream()
        .filter(book->book.id().equals(id))
        .findFirst()
        .orElse(null);
        }


}