package dsbd2020.lab.productmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan(basePackages = {"product"})
@EnableEurekaClient
public class ProductmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductmanagerApplication.class, args);
    }

}
