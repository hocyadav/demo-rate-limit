package com.example.demoratelimit.services;

import com.example.demoratelimit.domains.Requests;
import lombok.SneakyThrows;

import java.util.Deque;

/**
 * Rate limit for each service
 */
public interface IRateLimit {

    /**
     *
     * @param service : service name
     * @param requests : request dummy entity for tracking time
     * @return true is request is allowed else false
     */
    @SneakyThrows
    boolean checkForRateLimit(String service, Requests requests);

    Deque<Requests> getStatus();
}
