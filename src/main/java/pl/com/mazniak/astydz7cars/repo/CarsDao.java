package pl.com.mazniak.astydz7cars.repo;

import pl.com.mazniak.astydz7cars.model.Car;
import pl.com.mazniak.astydz7cars.model.Color;

import java.util.List;

public interface CarsDao {
    void fillDatabase();
    boolean addCar(String brand, String model, Color color, int year);
    List<Car> findAll();
    Car findById(long id);
    List<Car> findByYear(int year);
    List<Car> findByYearsRange(int begin, int end);
    void updateCar(Car car);
    void deleteCar(long id);
}
