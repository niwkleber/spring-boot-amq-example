package br.com.niwkleber.controller;

import jakarta.jms.Queue;
import lombok.extern.log4j.Log4j2;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController()
@RequestMapping("/active")
public class ActiveController {

    @Value("queue.name")
    private String queueName;

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/message")
    public String message(@RequestBody String message) {
        log.debug("Start request");
        long start = System.currentTimeMillis();

        Queue queue =  new ActiveMQQueue(this.queueName);
        jmsTemplate.convertAndSend(queue, message);

        double time = (System.currentTimeMillis() - start) / 1000.0;
        if (time > 1) {
            log.warn(String.format(":( The request was slow, taking %.8f seconds", time));
        } else {
            log.info(String.format("Request time %.8f seconds", time));
        }

        log.debug("End request");
        return "Message sent";
    }
}
