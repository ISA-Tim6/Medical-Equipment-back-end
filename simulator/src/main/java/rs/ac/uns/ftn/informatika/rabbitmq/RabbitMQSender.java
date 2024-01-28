package rs.ac.uns.ftn.informatika.rabbitmq;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class RabbitMQSender {
    private RabbitTemplate rabbitTemplate;
    private CoordinateSenderService service;
   
    public RabbitMQSender(RabbitTemplate rabbitTemplate, CoordinateSenderService s) {
        this.rabbitTemplate = rabbitTemplate;
        this.service = s;
    }
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;
    public void send(String timeInterval) throws JsonProcessingException, ExecutionException, InterruptedException{
    	List<Producer> coords = this.service.sendCoordinate(45, 45, 0);
    	for(Producer p: coords)
    	{
    		rabbitTemplate.convertAndSend(exchange,routingkey, p);
    		this.sleep(Math.round(Double.parseDouble(timeInterval)*1000));
    	}
    }
    
    private void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
