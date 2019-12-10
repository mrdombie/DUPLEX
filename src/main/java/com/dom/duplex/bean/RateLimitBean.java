package com.dom.duplex.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.util.concurrent.RateLimiter;

@Configuration
public class RateLimitBean {

	@Bean
	public RateLimiter getRateLimit() {
		final RateLimiter limit = RateLimiter.create(1);
		return limit;
	}
}
