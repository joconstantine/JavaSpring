package com.photoapp.discovery.api.gateway;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class GlobalFilterConfigurations {

	@Order(1)
	@Bean
	public GlobalFilter secondPrefilter() {
		
		return (exchange, chain) -> {
			
			log.info("My second pre-filter is executed...");
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				log.info("My third post-filter was executed...");
			}));		
		};
	}
	
	@Order(2)
	@Bean
	public GlobalFilter thirdPrefilter() {
		
		return (exchange, chain) -> {
			
			log.info("My third pre-filter is executed...");
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				log.info("My second post-filter was executed...");
			}));		
		};
	}
	
	@Order(3)
	@Bean
	public GlobalFilter fourthPrefilter() {
		
		return (exchange, chain) -> {
			
			log.info("My fourth pre-filter is executed...");
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				log.info("My first post-filter was executed...");
			}));		
		};
	}
}
