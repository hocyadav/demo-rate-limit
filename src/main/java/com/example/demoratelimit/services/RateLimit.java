package com.example.demoratelimit.services;

import com.example.demoratelimit.config.RateLimitConfig;
import com.example.demoratelimit.config.RateLimitConfig.Config;
import com.example.demoratelimit.domains.Requests;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Hariom Yadav
 * @since 28-Dec-2023
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class RateLimit implements IRateLimit {
    Deque<Requests> queue = new LinkedList<>(); // delete -> [...] <- add
    private final RateLimitConfig rateLimitConfig;

    // r1 : 4:8:1 , r2 : 4:8:2
    @Override
    @SneakyThrows
    public boolean checkForRateLimit(String service, Requests requests){
        Thread.sleep(2000);
        Config config = rateLimitConfig.getConfigMap().get(service);

        if (requests.getTime() == null) requests.setTime(OffsetDateTime.now());

        long now = OffsetDateTime.now().toEpochSecond();
        if (!queue.isEmpty())
            log.info("time diff = " + (now - queue.peekFirst().getTime().toEpochSecond()) );

        // clean out of window requests
        while (!queue.isEmpty() &&
                now - queue.peekFirst().getTime().toEpochSecond() == config.getTimeWindow()) {
            log.info("Deleting out of limit requests : " + queue.peekFirst());
            queue.pollFirst();
        }

        // block requests
        if (queue.size() >= config.getMaxRequestPerWindow()) {
            log.info("Rate limit not allowing request, reached max window size for this service : " + queue.size());
            queue.addLast(requests);
            return false;
        }

        // allow requests
        queue.addLast(requests);
        log.info("Allowing requests : " + requests);

        return true;
    }

    @Override
    public Deque<Requests> getStatus(){
        return this.queue;
    }

}
