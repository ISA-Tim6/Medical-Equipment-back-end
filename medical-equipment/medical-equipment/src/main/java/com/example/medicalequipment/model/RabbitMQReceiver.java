package com.example.medicalequipment.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.medicalequipment.iservice.ICompanyService;
import com.example.medicalequipment.iservice.IContractCompanyService;

@Component
public class RabbitMQReceiver implements RabbitListenerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(Location loc) {
        logger.info("User Details Received is.. " + loc.toString());
    }
    
    @Autowired
    private IContractCompanyService contractCompanyService;
    @Autowired
    private ICompanyService companyService;
    @RabbitListener(queues = "${spring.rabbitmq.queue1}")
    public void receivedMessage(Contract c) {
        logger.info("Contract is.. " + c.toString());
        boolean equipmentExists = false;
        ContractCompany contractCompany = new ContractCompany(c.getEquipment(),c.getDayInMonth(),c.getCompany(),c.getQuantity());
        Company storedCompany = companyService.findOneByName(contractCompany.getCompanyName());
        if(storedCompany != null)
        {
        	if(storedCompany.getEquipment() != null)
        	{
        		for(Equipment storedEquipment : storedCompany.getEquipment())
            	{
            		if(storedEquipment.getName().equalsIgnoreCase(contractCompany.getEquipment()))
            		{
            			equipmentExists = true;
            			break;
            		}
            	}
            	if(equipmentExists)
            	{
            		//brisanje prethodnih ugovora za tu kompaniju
            		contractCompanyService.deleteAllForCompany(contractCompany.getCompanyName());
            		contractCompanyService.save(contractCompany);
            	}
            	else
            	     	logger.info("Nema opremeee");
        	}
        	
        	
        }
        else
        	logger.info("Nema kompanijeeee");
        
        
    }
    
    
}
