package com.example.medicalequipment.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.model.RabbitMQSender;

@RestController
@RequestMapping(value = "api/foo/")
public class ProducerController {
	
	private RabbitMQSender rabbitMqSender;
    public ProducerController(RabbitMQSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }
    
    @Value("${app.message}")
    private String message;
    
    @PostMapping(value = "producer")
    public String publishUserDetails(@RequestBody String message) {
        rabbitMqSender.send(message);
        System.out.println(message.toString());
        return message;
    }
    
    @PostMapping(value = "producer1")
    public String publishDelivery(@RequestBody String message) {
        rabbitMqSender.sendMessage(message);
        System.out.println(message.toString());
        return message;
    }
}