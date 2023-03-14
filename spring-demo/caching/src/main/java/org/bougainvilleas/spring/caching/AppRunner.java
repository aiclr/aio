package org.bougainvilleas.spring.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger=LoggerFactory.getLogger(AppRunner.class);

    private final BookRepository bookRepository;

    public AppRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        simple();
        aspectJ();

    }

    private void aspectJ() {
        logger.info(".... Fetching booksAspectJ");
        logger.info("isbn-1234-->{}",bookRepository.getByIsbnAspectJ("isbn-1234"));
        logger.info("isbn-4567-->{}",bookRepository.getByIsbnAspectJ("isbn-4567"));
        logger.info("isbn-1234-->{}",bookRepository.getByIsbnAspectJ("isbn-1234"));
        logger.info("isbn-4567-->{}",bookRepository.getByIsbnAspectJ("isbn-4567"));
        logger.info("isbn-1234-->{}",bookRepository.getByIsbnAspectJ("isbn-1234"));
        logger.info("isbn-1234-->{}",bookRepository.getByIsbnAspectJ("isbn-1234"));
    }

    private void simple() {
        logger.info(".... Fetching books");
        logger.info("isbn-1234-->{}",bookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-4567-->{}",bookRepository.getByIsbn("isbn-4567"));
        logger.info("isbn-1234-->{}",bookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-4567-->{}",bookRepository.getByIsbn("isbn-4567"));
        logger.info("isbn-1234-->{}",bookRepository.getByIsbn("isbn-1234"));
        logger.info("isbn-1234-->{}",bookRepository.getByIsbn("isbn-1234"));
    }


}
