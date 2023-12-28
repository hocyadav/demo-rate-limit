package com.example.demoratelimit.exceptions;

/**
 * @author Hariom Yadav
 * @since 28-Dec-2023
 */
public class RateLimitNotConfiguredException extends RuntimeException {

    public RateLimitNotConfiguredException(String message) {
        super(message);
    }
}
