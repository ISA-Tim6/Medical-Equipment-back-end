package rs.ac.uns.ftn.informatika.rabbitmq;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoordinateSenderService {

    private final RabbitTemplate rabbitTemplate;
    private final CoordinateGeneratorService coordinateGeneratorService;
    private final ObjectMapper objectMapper;
    

    public CoordinateSenderService(RabbitTemplate rabbitTemplate, CoordinateGeneratorService coordinateGeneratorService, ObjectMapper objectMapper
                                   ) {
        this.rabbitTemplate = rabbitTemplate;
        this.coordinateGeneratorService = coordinateGeneratorService;
        this.objectMapper = objectMapper;
        
    }

    public List<Producer> sendCoordinate(double startLatitude, double startLongitude, int bloodUnist) throws JsonProcessingException, ExecutionException, InterruptedException {
        
        if(startLatitude == -1 && startLatitude==-1){
            Producer endSight = new Producer(-1.0, -1.0);
           // String jsonCoordinate = objectMapper.writeValueAsString(endSight);
            //rabbitTemplate.convertAndSend("vehicle-coordinates-queue", jsonCoordinate);
            return null;
        }
        List<Producer> northernCoordinates = new ArrayList<>();
        northernCoordinates.add(new Producer(51.5074, -0.1278));
        northernCoordinates.add(new Producer(55.7558, 37.6176));
        northernCoordinates.add(new Producer(59.3293, 18.0686));
        northernCoordinates.add(new Producer(52.5200, 13.4050));
        northernCoordinates.add(new Producer(59.9139, 10.7522));

        Random random = new Random();
        int randomIndex = random.nextInt(northernCoordinates.size());
        Producer bloodBankCordinates = northernCoordinates.get(randomIndex);

        List<Producer> coord =  coordinateGeneratorService.generateCoordinates(startLatitude, startLongitude, bloodBankCordinates.getLatitude(), bloodBankCordinates.getLongitude(), 15);
        int time =2;
        for (Producer c: coord){
            //time = refreshPeriodService.findRefreshPeriodAsync(userService.getAuthUser()).get();
            //String jsonCoordinate = objectMapper.writeValueAsString(c);
            //rabbitTemplate.convertAndSend("vehicle-coordinates-queue", jsonCoordinate);
            //sleep(time);
        }
        System.out.println("Kretanje vozila zavrseno!");
        return coord;
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
