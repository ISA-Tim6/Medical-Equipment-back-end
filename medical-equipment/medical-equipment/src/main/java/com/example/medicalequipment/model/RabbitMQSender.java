package com.example.medicalequipment.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.medicalequipment.iservice.ICompanyService;
import com.example.medicalequipment.iservice.IContractCompanyService;
import com.example.medicalequipment.repository.IContractRepository;

@Service
public class RabbitMQSender {
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IContractRepository contractRepository;
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
    
    
    @Scheduled(cron = "0 0 10 * * ?")	//svakog dana u 10h
    public void sendDelivery(){
    	int todayDayOfMonth = LocalDate.now().getDayOfMonth();
    	List<ContractCompany> all = contractRepository.findAll();
    	for(ContractCompany contract : all)
    	{
    		if(todayDayOfMonth == contract.getDayInMonth())
    		{
    			rabbitTemplate.convertAndSend(exchange, routingkey2, "Your equipment is successfully delivered.");
    		}
    	}
        
    }
    
    
}
