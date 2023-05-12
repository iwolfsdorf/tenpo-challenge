package com.tenpo.api.services;

import com.tenpo.api.exceptions.ProviderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class ExternalService {

    private static final Double PERCENTAGE_DEFAULT = 10.0;
    Logger log = LoggerFactory.getLogger(ExternalService.class);
    private Double lastPercentage;

    @Cacheable("percentage")
    @Retryable(value = {ProviderException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public Double getPercentage() throws ProviderException {
        log.info("Trying get percentage of external service");
        try {
            // call external service here
            lastPercentage = callService();
        } catch (ProviderException ex) {
            if (lastPercentage != null) {
                return lastPercentage;
            }
            throw ex;
        }
        return lastPercentage;
    }

    private Double callService() throws ProviderException {
        try {
            return PERCENTAGE_DEFAULT;
        } catch (Exception ex) {
            throw new ProviderException();
        }
    }
}