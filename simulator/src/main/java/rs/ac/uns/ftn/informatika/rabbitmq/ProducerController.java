package rs.ac.uns.ftn.informatika.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/")
public class ProducerController {
	
	private RabbitMQSender rabbitMqSender;
    public ProducerController(RabbitMQSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }
    
    @Value("${app.message}")
    private String message;
    
    @PostMapping(value = "producer")
    public String publishUserDetails(@RequestBody Producer user) {
        rabbitMqSender.send(user);
        System.out.println(user.toString());
        return message;
    }
}