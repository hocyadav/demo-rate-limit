package com.example.demoratelimit.services;

import com.example.demoratelimit.config.RateLimitConfig;
import com.example.demoratelimit.domains.Requests;
import com.example.demoratelimit.exceptions.RateLimitNotConfiguredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Hariom Yadav
 * @since 28-Dec-2023
 */
@Slf4j
@Service
public class RateLimitService {
    // key : service info , Rate limit
    private final Map<String, IRateLimit> rateLimitMap;
    private final RateLimitConfig rateLimitConfig;



    public RateLimitService(RateLimitConfig rateLimitConfig) {
        this.rateLimitConfig = rateLimitConfig;
        this.rateLimitMap  = new ConcurrentHashMap<>();
        for (Map.Entry<String, RateLimitConfig.Config> e : rateLimitConfig.getConfigMap().entrySet()) {
            rateLimitMap.put(e.getKey(), new RateLimit(rateLimitConfig));
        }
    }

    public boolean validateRequestPerSystemLevel(String service, Requests requests) {
        System.out.println("rateLimitMap = " + rateLimitMap);
        Optional<IRateLimit> rateLimit = Optional.ofNullable(rateLimitMap.get(service));
        if (!rateLimit.isPresent()) {
            log.info("Rate limit not configured for this service : " + service);
            throw new RateLimitNotConfiguredException("Rate limit not configured for this service : " + service);
        }
        return rateLimit.get().checkForRateLimit(service, requests);
    }

}
