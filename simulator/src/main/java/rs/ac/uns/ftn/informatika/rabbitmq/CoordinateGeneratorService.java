package rs.ac.uns.ftn.informatika.rabbitmq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CoordinateGeneratorService {

    public List<Producer> generateCoordinates(double startLatitude, double startLongitude,
                                                double endLatitude, double endLongitude, int steps) {
        List<Producer> coordinates = new ArrayList<>();

        double latitudeStep = (endLatitude - startLatitude) / steps;
        double longitudeStep = (endLongitude - startLongitude) / steps;

        double currentLatitude = startLatitude;
        double currentLongitude = startLongitude;

        for (int i = 0; i <= steps; i++) {
            coordinates.add(new Producer(currentLatitude, currentLongitude));

            currentLatitude += latitudeStep;
            currentLongitude += longitudeStep;
        }

        return coordinates;
    }
}