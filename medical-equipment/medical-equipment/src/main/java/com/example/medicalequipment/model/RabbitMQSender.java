package com.example.medicalequipment.model;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.ICompanyService;
import com.example.medicalequipment.iservice.IContractCompanyService;

@Service
public class RabbitMQSender {
    private RabbitTemplate rabbitTemplate;
    @Autowired
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    
    @Value("${spring.rabbitmq.routingkeySimulator}")
    private String routingkeySimulator;
    public void send(String message){
        rabbitTemplate.convertAndSend(exchange,routingkeySimulator, message);
    }
    
    @Value("${spring.rabbitmq.routingkey2}")
    private String routingkey2;
    public void sendMessage(String message){
        rabbitTemplate.convertAndSend(exchange,routingkey2, message);
    }
    
    @Scheduled(cron = "0 0 0 1 * ?")//u ponoc prvog u mjesecu
    //@Value("${spring.rabbitmq.routingkey2}")
    private String routingkey23;
    public void sendMessage3(String message){
        rabbitTemplate.convertAndSend(exchange,routingkey2, message);
    }
    
    
}
