package com.tenpo.api.controllers;

import com.tenpo.api.entities.Result;
import com.tenpo.api.exceptions.ApiException;
import com.tenpo.api.services.ApiService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;

@RestController
public class ApiController {

    private final ApiService apiService;
    private final Bucket bucket;
    CacheManager cacheManager;

    public ApiController(ApiService apiService, CacheManager cacheManager) {
        this.apiService = apiService;
        this.cacheManager = cacheManager;
        Bandwidth limit = Bandwidth.classic(3, Refill.greedy(3, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @PostMapping("/sum")
    public ResponseEntity<String> sumNumbers(@RequestParam Double num1, @RequestParam Double num2) {
        if (bucket.tryConsume(1)) {
            try {
                Double value = apiService.sumWithPercentage(num1, num2);
                return ResponseEntity.ok(value.toString());
            } catch (ApiException e) {
                throw new ResponseStatusException(e.getHttpStatus());
            }
        }

        throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
    }

    @GetMapping("/results")
    public ResponseEntity<Page<Result>> getResults(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        if (bucket.tryConsume(1)) {
            try {
                Page<Result> apiCalls = apiService.getResults(page, size);
                return ResponseEntity.ok(apiCalls);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS);
    }
}