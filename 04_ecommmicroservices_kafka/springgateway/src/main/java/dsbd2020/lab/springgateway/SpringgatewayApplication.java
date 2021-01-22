package dsbd2020.lab.springgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringgatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/user/**")
                        //.filters(f -> f.rewritePath("/user/(?<RID>.*)", "/${RID}"))
                        .uri("http://usermanager:2222"))
                .route(p -> p
                        .path("/product/**")
                        //.filters(f -> f.rewritePath("/product/(?<RID>.*)", "/${RID}"))
                        .uri("http://productmanager:3333"))
                .route(p -> p
                        .path("/order/**")
                        //.filters(f -> f.rewritePath("/order/(?<RID>.*)", "/${RID}"))
                        .uri("http://ordermanager:4444"))
                .build();
    }

}
