package dsbd2020.lab.ordermanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"order","product","user"})
public class OrdermanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdermanagerApplication.class, args);
    }

}
