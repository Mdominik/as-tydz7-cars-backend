package pl.com.mazniak.astydz7cars.repo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.com.mazniak.astydz7cars.model.Car;
import pl.com.mazniak.astydz7cars.model.Color;
import pl.com.mazniak.astydz7cars.service.CarService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarsDaoImpl implements CarsDao{
    private JdbcTemplate jdbcTemplate;
    private CarService carService;

    @Autowired
    public CarsDaoImpl(JdbcTemplate jdbcTemplate, CarService carService) {
        this.carService = carService;
        this.jdbcTemplate = jdbcTemplate;

    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDatabase() {
        String sql = "INSERT INTO cars (brand, model, color, year) VALUES(?, ?, ?, ?)";
        carService.populateCarList().stream().forEach(car -> jdbcTemplate.update(sql,
                car.getBrand(), car.getModel(), car.getColor().name(), car.getProductionYear()));
    }

    @Override
    public boolean addCar(String brand, String model, Color color, int year) {
        Car car = new Car(brand, model, color, year);
        String sql = "INSERT INTO cars (brand, model, color, year) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getBrand(), car.getModel(), car.getColor().name(), car.getProductionYear());
        return true;
    }
    @Override
    public List<Car> findAll() {
        String sql = "SELECT * FROM cars";
        List<Car> carsList = new ArrayList<>();

        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(element -> carsList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("brand")),
                String.valueOf(element.get("model")),
                Color.valueOf(element.get("color").toString()),
                Integer.parseInt(String.valueOf(element.get("year"))))));
        return carsList;
    }

    @Override
    public Car findById(long id) {
        String sql = "SELECT * FROM cars WHERE car_id = ?";
        Car car = null;
        try {
            car = jdbcTemplate.queryForObject(sql, (resultSet, i) -> new Car(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    Color.valueOf(resultSet.getObject(4).toString()),
                    resultSet.getInt(5)), id);
        } catch(EmptyResultDataAccessException erdae) {
            return car;
        }
        return car;
    }

    @Override
    public List<Car> findByYear(int year) {
        String sql = "SELECT * FROM cars WHERE year = ?";
        List<Car> carsList = new ArrayList<>();

        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql, year);
        maps.stream().forEach(element -> carsList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("brand")),
                String.valueOf(element.get("model")),
                Color.valueOf(String.valueOf(element.get("color"))),
                Integer.parseInt(String.valueOf(element.get("year"))))));
        return carsList;
    }

    @Override
    public List<Car> findByYearsRange(int begin, int end) {
        String sql = "SELECT * FROM cars WHERE year BETWEEN ? AND ?";
        List<Car> carsList = new ArrayList<>();

        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql, begin, end);
        maps.stream().forEach(element -> carsList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("brand")),
                String.valueOf(element.get("model")),
                Color.valueOf(String.valueOf(element.get("color"))),
                Integer.parseInt(String.valueOf(element.get("year"))))));
        return carsList;
    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public void deleteCar(long id) {

    }

}
