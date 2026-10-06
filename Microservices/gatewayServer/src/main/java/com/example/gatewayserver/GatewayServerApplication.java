package com.example.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }


    @Bean
    public RouteLocator routeLocatorConfig(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r
                        .path("/dm/accounts/**")
                        .filters(f-> f.rewritePath("/dm/accounts/(?<segment>.*)", "/${segment}" )
                                 .addResponseHeader("DM-Response-Time", "ACCOUNTS" + (LocalDateTime.now().toString())))
                        .uri("lb://ACCOUNTS"))
                .route(r -> r
                        .path("/dm/cards/**")
                        .filters(f-> f.rewritePath("/dm/cards/(?<segment>.*)", "/${segment}" )
                                .addResponseHeader("DM-Response-Time", "CARDS" + (LocalDateTime.now().toString())))
                        .uri("lb://CARDS"))
                .route(r -> r
                        .path("/dm/loans/**")
                        .filters(f-> f.rewritePath("/dm/loans/(?<segment>.*)", "/${segment}" )
                                .addResponseHeader("DM-Response-Time", "LOANS" + (LocalDateTime.now().toString())))

                        .uri("lb://LOANS"))
                .build();


    }

}
