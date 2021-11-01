package com.vladan.api.validator.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository repository) {
        return args -> {
            log.info("Loading " + repository.save(new Customer("Jeff Bridges", "123456789", "(123)-456-7890", "vladan@mail.com", LocalDate.of(1949, 12,4))));
            log.info("Loading " + repository.save(new Customer("John Malkovich", "987654321", "(123)-456-7890", "john@mail.com", LocalDate.of(1953, 12,9))));
        };
    }
}
