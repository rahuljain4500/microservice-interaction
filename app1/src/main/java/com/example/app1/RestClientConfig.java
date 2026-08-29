package com.example.app1;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    /**
     * Load-balanced builder: service names like http://APP2 are resolved
     * through Eureka + Spring Cloud LoadBalancer instead of a fixed host/port.
     */
    @Bean
    @LoadBalanced
    RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    RestClient app2RestClient(@LoadBalanced RestClient.Builder builder) {
        return builder.build();
    }
}
