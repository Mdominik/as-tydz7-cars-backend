package pl.com.mazniak.astydz7cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.mazniak.astydz7cars.config.CarsDBConfig;
import pl.com.mazniak.astydz7cars.model.Car;
import pl.com.mazniak.astydz7cars.utils.CarsLoader;

import java.util.List;

@Service
public class CarService {
    CarsLoader carsLoader;

    @Autowired
    public CarService(CarsLoader carsLoader) {
        this.carsLoader = carsLoader;
    }

    public List<Car> populateCarList() {
        return carsLoader.loadSampleCars();
    }




}
