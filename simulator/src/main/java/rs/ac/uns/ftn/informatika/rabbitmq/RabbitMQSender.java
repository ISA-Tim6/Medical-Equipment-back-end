package rs.ac.uns.ftn.informatika.rabbitmq;

import java.util.ArrayList;
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
   
    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;
    public void send(String timeInterval) throws JsonProcessingException, ExecutionException, InterruptedException{
    	List<Producer> coords = new ArrayList<>();
    	coords.add(new Producer(45.244028564841, 19.85092364251614));
    	coords.add(new Producer(45.24436849619236, 19.85069297254086));
    	coords.add(new Producer(45.24466687870223, 19.850494489073757));
    	coords.add(new Producer(45.244912382123715, 19.850338920950893));
    	coords.add(new Producer(45.245138999724944, 19.850188717246056));
    	coords.add(new Producer(45.24543737818778, 19.849990233778957));
    	coords.add(new Producer(45.24579996256492, 19.849775657057766));
    	coords.add(new Producer(45.24576219346693, 19.84942696988583));
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
