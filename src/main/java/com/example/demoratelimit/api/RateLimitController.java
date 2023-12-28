package com.example.demoratelimit.api;

import com.example.demoratelimit.domains.Requests;
import com.example.demoratelimit.services.IRateLimit;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hariom Yadav
 * @since 28-Dec-2023
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RateLimitController {
    private final IRateLimit rateLimit;

    @GetMapping("/rate-limit/{service}")
    public boolean validateRequests(@PathVariable String service){
        return rateLimit.checkForRateLimit(service, Requests.builder().build());
    }

}
