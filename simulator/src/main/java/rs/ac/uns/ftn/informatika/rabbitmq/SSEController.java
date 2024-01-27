package rs.ac.uns.ftn.informatika.rabbitmq;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

import org.springframework.http.codec.ServerSentEvent;


@RestController
@RequestMapping(value = "api/")
public class SSEController {

    private final RabbitMQReceiver rabbitMQReceiver;

    public SSEController(RabbitMQReceiver rabbitMQReceiver) {
        this.rabbitMQReceiver = rabbitMQReceiver;
    }

    @GetMapping(value = "events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamEvents() {
        return rabbitMQReceiver.receiveMessages()
                .map(message -> ServerSentEvent.<String>builder()
                        .data(message)
                        .build());
    }
    
}

