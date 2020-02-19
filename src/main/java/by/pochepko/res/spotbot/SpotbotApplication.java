package by.pochepko.res.spotbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.ApiContextInitializer;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories("by.pochepko.res.spotbot.repository")
@EntityScan("by.pochepko.res.spotbot.model")
public class SpotbotApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotbotApplication.class, args);
    }
    @PostConstruct
    public void init(){
        ApiContextInitializer.init();
    }

}
