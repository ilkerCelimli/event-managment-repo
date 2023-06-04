package com.portifolyo.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.net.URI;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r -> r.path("/user/**")
						.uri("http://localhost:9011/"))

				.route(r -> r.path("/event/**")
						.uri("http://localhost:9001/"))
				.build();

	}

}
