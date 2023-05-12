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

    Logger log = LoggerFactory.getLogger(ExternalService.class);

    private static final Double PERCENTAGE_DEFAULT = 10.0;

    @Cacheable("percentage")
    @Retryable(value = {ProviderException.class},maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public Double getPercentage() throws ProviderException {
        Double lastPercentage = null;
        log.info("Trying get percentage of external service");
        try {
            // call external service here
            lastPercentage = PERCENTAGE_DEFAULT;
        } catch (Exception ex) {
            if(lastPercentage != null) {
                return lastPercentage;
            }
            throw new ProviderException();
        }
        return lastPercentage;
    }
}