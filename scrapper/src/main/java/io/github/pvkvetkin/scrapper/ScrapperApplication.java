package io.github.pvkvetkin.scrapper;

import io.github.pvkvetkin.scrapper.configuration.ApplicationConfig;
import io.github.pvkvetkin.scrapper.configuration.ContentClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication()
@EnableConfigurationProperties({
    ApplicationConfig.class,
    ContentClientConfig.class
})
public class ScrapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrapperApplication.class, args);
    }
}
