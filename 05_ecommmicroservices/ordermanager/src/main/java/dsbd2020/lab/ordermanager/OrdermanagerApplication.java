package dsbd2020.lab.ordermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan(basePackages = {"order","product","user"})
@EnableEurekaClient
public class OrdermanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdermanagerApplication.class, args);
    }

}
