package com.vladan.api.validator.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class ArticleLookupService {

    private static final Logger log = LoggerFactory.getLogger(ArticleLookupService.class);

    private final RestTemplate restTemplate;

    public ArticleLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public CompletableFuture<Article> findArticle(String article) throws InterruptedException {
        log.info("Looking for article: " + article);
        String url = String.format("https://jsonplaceholder.typicode.com/posts/%s", article);
        Article result = restTemplate.getForObject(url, Article.class);
        //The sleep is to simulate delay and to demo the async nature of the REST calls using this method
//        Thread.sleep(5000);
        return CompletableFuture.completedFuture(result);
    }
}
