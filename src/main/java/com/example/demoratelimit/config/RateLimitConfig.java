package com.example.demoratelimit.config;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hariom Yadav
 * @since 28-Dec-2023
 */
@Component
@Data
public class RateLimitConfig {
    Map<String, Config> configMap = new HashMap<>();

    @Data @ToString @Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Config {
        Integer timeWindow;
        Integer maxRequestPerWindow;
    }
}
