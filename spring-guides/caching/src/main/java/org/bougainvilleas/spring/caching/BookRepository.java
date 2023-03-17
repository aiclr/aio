package org.bougainvilleas.spring.caching;

public interface BookRepository {
    Book getByIsbn(String isbn);
    Book getByIsbnAspectJ(String isbn);
}
