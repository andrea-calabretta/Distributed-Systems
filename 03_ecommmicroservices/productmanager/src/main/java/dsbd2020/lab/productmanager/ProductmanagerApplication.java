package dsbd2020.lab.productmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"product"})
public class ProductmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductmanagerApplication.class, args);
    }

}
