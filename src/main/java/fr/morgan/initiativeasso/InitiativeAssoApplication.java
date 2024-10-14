package fr.morgan.initiativeasso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class InitiativeAssoApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitiativeAssoApplication.class, args);
    }

}
