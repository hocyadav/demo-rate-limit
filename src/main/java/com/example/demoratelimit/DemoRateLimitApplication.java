package com.example.demoratelimit;

import com.example.demoratelimit.config.RateLimitConfig;
import com.example.demoratelimit.domains.Requests;
import com.example.demoratelimit.services.IRateLimit;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoRateLimitApplication implements ApplicationRunner {
	private final RateLimitConfig  rateLimitConfig;

	private final IRateLimit rateLimit;

	public static void main(String[] args) {
		SpringApplication.run(DemoRateLimitApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		rateLimitConfig.setConfigMap(Map.of(
				"service1", RateLimitConfig.Config.builder()
								.maxRequestPerWindow(5)
								.timeWindow(8)// sec
						.build()

		));
		System.out.println("rateLimitConfig = " + rateLimitConfig);
        System.out.println("check = " + rateLimit.checkForRateLimit("service1", Requests.builder().build()));
        System.out.println("check = " + rateLimit.checkForRateLimit("service1", Requests.builder().build()));
        System.out.println("check = " + rateLimit.checkForRateLimit("service1", Requests.builder().build()));
        System.out.println("check = " + rateLimit.checkForRateLimit("service1", Requests.builder().build()));
        System.out.println("check = " + rateLimit.checkForRateLimit("service1", Requests.builder().build()));
        System.out.println("check = " + rateLimit.checkForRateLimit("service1", Requests.builder().build()));
        System.out.println("check = " + rateLimit.checkForRateLimit("service1", Requests.builder().build()));
        System.out.println("check = " + rateLimit.checkForRateLimit("service1", Requests.builder().build()));

        System.out.println("status = " + rateLimit.getStatus());

	}
}
