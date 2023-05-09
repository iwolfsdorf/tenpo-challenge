package com.tenpo.api.services;

import com.tenpo.api.controllers.errorhandling.ApiException;
import com.tenpo.api.entities.Result;
import com.tenpo.api.repositories.ResultsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApiService {

    private final ExternalService externalService;
    private final ResultsRepository resultsRepository;
    //private final RateLimitInterceptor rateLimitInterceptor;

    public ApiService(ExternalService externalService, ResultsRepository resultsRepository/*, RateLimitInterceptor rateLimitInterceptor*/) {
        this.externalService = externalService;
        this.resultsRepository = resultsRepository;
        //this.rateLimitInterceptor = rateLimitInterceptor;
    }

    public Double sumWithPercentage(Double num1, Double num2) throws ApiException {
        Double percentage = externalService.getPercentage();
        Double valueResult = (num1 + num2) * (1 + percentage / 100);
        Result result = new Result(LocalDateTime.now(), num1, num2, percentage, valueResult);
        resultsRepository.save(result);
        return valueResult;
    }

    public Page<Result> getApiCalls(Integer page, Integer size) {
        return resultsRepository.findAll(PageRequest.of(page, size, Sort.by("dateTime").descending()));
    }
/*
    public void checkRateLimit() throws TooManyRequestsException {
        rateLimitInterceptor.checkRateLimit();
    }*/
}