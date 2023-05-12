package com.tenpo.api.manager;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

public class CaffeineCacheManager {
    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(300, TimeUnit.SECONDS)
                .initialCapacity(10);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        org.springframework.cache.caffeine.CaffeineCacheManager caffeineCacheManager = new org.springframework.cache.caffeine.CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
