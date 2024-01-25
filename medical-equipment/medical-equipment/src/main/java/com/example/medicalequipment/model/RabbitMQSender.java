package com.example.medicalequipment.model;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
}
