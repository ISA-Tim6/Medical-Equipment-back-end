package rs.ac.uns.ftn.informatika.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*
 * 
 * Za pokretanje primera potrebno je instalirati RabbitMQ - https://www.rabbitmq.com/download.html
 */
@SpringBootApplication
public class RabbitmqProducerExampleApplication {

	 @Value("${spring.rabbitmq.host}")
	    String host;
	    @Value("${spring.rabbitmq.username}")
	    String username;
	    @Value("${spring.rabbitmq.password}")
	    String password;
	    
	    @Value("${spring.rabbitmq.queueSimulator}")
	    private String queueSimulator;
	    @Value("${spring.rabbitmq.exchange}")
	    private String exchange;
	    @Value("${spring.rabbitmq.routingkeySimulator}")
	    private String routingkeySimulator;
	    
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerExampleApplication.class, args);
	}

	 @Bean
	    Queue queue() {
	        return new Queue(queueSimulator, true);
	 }
	 
	 @Bean
	    Exchange myExchange() {
	        return ExchangeBuilder.directExchange(exchange).durable(true).build();
	    }
	    @Bean
	    Binding binding() {
	        return BindingBuilder
	                .bind(queue())
	                .to(myExchange())
	                .with(routingkeySimulator)
	                .noargs();
	  }
	 
	@Bean
    CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
