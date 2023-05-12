package com.tenpo.api.services;

import com.tenpo.api.entities.Result;
import com.tenpo.api.exceptions.ApiException;
import com.tenpo.api.exceptions.ProviderException;
import com.tenpo.api.repositories.ResultsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApiService {

    private final ExternalService externalService;
    private final ResultsRepository resultsRepository;

    Logger log = LoggerFactory.getLogger(ApiService.class);

    public ApiService(ExternalService externalService, ResultsRepository resultsRepository) {
        this.externalService = externalService;
        this.resultsRepository = resultsRepository;
    }

    public Double sumWithPercentage(Double num1, Double num2) throws ApiException {
        try {
            Double percentage = externalService.getPercentage();
            Double value = (num1 + num2) * (1 + percentage / 100);
            Result result = new Result(LocalDateTime.now(), num1, num2, percentage, value);
            saveResult(result);
            return value;
        } catch (ProviderException e) {
            throw new ApiException(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    public Page<Result> getResults(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return resultsRepository.findAllByOrderByDateTimeDesc(pageable);
    }

    @Async
    protected void saveResult(Result result) {
        try {
            resultsRepository.save(result);
        } catch (Exception e) {
            log.error("Unexpected error trying save result in DB: " + e.getMessage());
        }
    }

}