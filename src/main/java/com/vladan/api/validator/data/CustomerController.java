package com.vladan.api.validator.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@RestController
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerRepository repository;

    private final ArticleLookupService articleService;

    public CustomerController(CustomerRepository repository, ArticleLookupService articleService) {
        this.repository = repository;
        this.articleService = articleService;
    }

    @GetMapping("/customers")
    List<Customer> all() {
        return repository.findAll();
    }

    @PostMapping("/customers")
    Customer newCustomer(@Valid @RequestBody Customer newCustomer) {
        try {
            //this is simulating three calls to validator services for SSN Validation, Phone Validation and Email Validation
            CompletableFuture<Article> article1 = articleService.findArticle("1");
            CompletableFuture<Article> article2 = articleService.findArticle("2");
            CompletableFuture<Article> article3 = articleService.findArticle("3");

            CompletableFuture.allOf(article1, article2, article3).join();

            log.info(article1.get().getTitle());
            log.info(article2.get().getTitle());
            log.info(article3.get().getTitle());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something unexpected happened: " + e.getMessage());
        }

        return repository.save(newCustomer);
    }
}
