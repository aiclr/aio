package org.bougainvilleas.spring.caching;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimpleBookRepository implements BookRepository {

    @Cacheable("books")
    @Override
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }

    @SlowService
    @Override
    public Book getByIsbnAspectJ(String isbn) {
        simulateSlowService();
        return new Book(isbn, "AspectJ Some book");
    }


    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
