package br.com.niwkleber;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@Configuration
@EnableJms
@Log4j2
public class Main {

    @Value("${spring.activemq.broker-url}")
    private String activeBrokerUrl;

    @Value("${queue.name}")
    private String queueName;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void run() {
        log.info(String.format("ActiveMQ Broker URL: %s", activeBrokerUrl));
        log.info(String.format("Queue Name: %s", queueName));
    }
}
