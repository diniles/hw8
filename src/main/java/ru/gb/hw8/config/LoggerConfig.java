package ru.gb.hw8.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gb.hw8.utils.Logger;

@Configuration
public class LoggerConfig {
    @Value("${lig.file.name:./hw8log.txt}")
    private String fileName;

    @Bean
    public Logger getLogger() {
        return new Logger(fileName);
    }
}
