package dsbd2020.lab.registrationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistrationserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationserverApplication.class, args);
    }

}
