package com.example.medicalequipment.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicalequipment.iservice.ICompanyService;
import com.example.medicalequipment.iservice.IContractCompanyService;
import com.example.medicalequipment.iservice.IReservationService;
import com.example.medicalequipment.model.Company;
import com.example.medicalequipment.model.ContractCompany;
import com.example.medicalequipment.model.Equipment;
import com.example.medicalequipment.model.RabbitMQSender;

@RestController
@RequestMapping(value = "api/foo/")
public class ProducerController {
	@Autowired
	private ICompanyService CompanyService;
	@Autowired
	private IContractCompanyService ContractService;
	private RabbitMQSender rabbitMqSender;
    public ProducerController(RabbitMQSender rabbitMqSender, ICompanyService companyService, IContractCompanyService contractService) {
        this.rabbitMqSender = rabbitMqSender;
        this.CompanyService = companyService;
        this.ContractService = contractService;
    }
    
    @Value("${app.message}")
    private String message;
    
    @PostMapping(value = "producer")
    public String publishUserDetails(@RequestBody String message) {
        rabbitMqSender.send(message);
        System.out.println(message.toString());
        return message;
    }
    
    @PostMapping(value = "producer1/{company}")		//
    public boolean cancelDelivery(@RequestBody String message, @PathVariable String company) throws UnsupportedEncodingException {
    	String companyDecoded = URLDecoder.decode(company, StandardCharsets.UTF_8.toString());
    	Company storedCompany = CompanyService.findOneByName(companyDecoded);
    	ContractCompany storedContract = ContractService.getAllForCompany(companyDecoded).get(0);
    	boolean isEnoughEquipment = false;
    	
    	//provjera da li ima dovoljno opreme
    	if(storedCompany != null)
        {
        	if(storedCompany.getEquipment() != null)
        	{
        		for(Equipment storedEquipment : storedCompany.getEquipment())
            	{
            		if(storedEquipment.getName().equalsIgnoreCase(storedContract.getEquipment()) && storedEquipment.getQuantity() >= storedContract.getQuantity())
            		{
            			isEnoughEquipment = true;
            			break;
            		}
            	}
        		if(isEnoughEquipment)
            	{
        			//provjeri da li je par dana prije datuma ugovora
        			 int todayDayOfMonth = LocalDate.now().getDayOfMonth();
        			 int threeDaysBefore = (storedContract.getDayInMonth() > 3) ? storedContract.getDayInMonth() - 3 : 28 + storedContract.getDayInMonth();
        			 boolean isThreeDaysBefore = todayDayOfMonth == threeDaysBefore;
        			 if(isThreeDaysBefore)
        			 {
        				rabbitMqSender.sendMessage(message);
             	        System.out.println(companyDecoded);
             	        return true;
        			 }
        			
            	}
            	
        	}
        	
        	
        }
    	
        String notSentMessage = "Impossible";
        System.out.println(notSentMessage);
        return false;
    }
}