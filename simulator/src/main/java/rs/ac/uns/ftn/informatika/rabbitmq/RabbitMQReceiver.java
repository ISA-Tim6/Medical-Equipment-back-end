package rs.ac.uns.ftn.informatika.rabbitmq;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;



@Component
public class RabbitMQReceiver implements RabbitListenerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);
    private RabbitMQSender sender;

    public RabbitMQReceiver(RabbitMQSender rabbitMqSender) {
    	sender = rabbitMqSender;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }
    @RabbitListener(queues = "${spring.rabbitmq.queueSimulator}")
    public void receivedMessage(String message) throws JsonProcessingException, ExecutionException, InterruptedException {
        logger.info("User Details Received is.. " + message.toString());
        sender.send(message);
    }
    
    
}
