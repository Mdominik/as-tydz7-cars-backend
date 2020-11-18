package pl.com.mazniak.astydz7cars.utils;

import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.com.mazniak.astydz7cars.model.Car;
import pl.com.mazniak.astydz7cars.model.Color;

import java.util.LinkedList;
import java.util.List;

@Service
public class CarsLoader {
    List<Car> carsList;

    public CarsLoader () {
        carsList = new LinkedList<>();
    }


    public List<Car> loadSampleCars() {
        carsList.add(new Car("VWw", "Up1!", Color.BLUE, 2027));
        carsList.add(new Car("Audwi", "A51", Color.RED, 2038));
        return carsList;
    }

}
