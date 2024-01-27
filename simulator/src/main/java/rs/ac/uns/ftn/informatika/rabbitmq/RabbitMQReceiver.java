package rs.ac.uns.ftn.informatika.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;


@Component
public class RabbitMQReceiver implements RabbitListenerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);
    private final UnicastProcessor<String> eventPublisher;

    public RabbitMQReceiver() {
        this.eventPublisher = UnicastProcessor.create();
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
    }
    @RabbitListener(queues = "${spring.rabbitmq.queueSimulator}")
    public void receivedMessage(String message) {
        logger.info("User Details Received is.. " + message.toString());
    }
    public Flux<String> receiveMessages() {
        return eventPublisher
                .replay(10)
                .autoConnect();
    }
    
    
}
