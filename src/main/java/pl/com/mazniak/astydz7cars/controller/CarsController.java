package pl.com.mazniak.astydz7cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.com.mazniak.astydz7cars.model.Car;
import pl.com.mazniak.astydz7cars.model.Color;
import pl.com.mazniak.astydz7cars.repo.CarsDaoImpl;
import pl.com.mazniak.astydz7cars.service.CarService;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/api/cars")
public class CarsController {
    CarsDaoImpl carsDaoImpl;
    CarService carService;

    @Autowired
    public CarsController(CarsDaoImpl carsDaoImpl, CarService carService) {
        this.carsDaoImpl = carsDaoImpl;
        this.carService = carService;
        this.carService.populateCarList();
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Car>> findAll() {
        List<Car> list = carsDaoImpl.findAll();
        return list.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND) : ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Car> findById(@PathVariable long id) {
        Car car = carsDaoImpl.findById(id);
        System.out.println(car.getId());
        return (car == null) ? new ResponseEntity(HttpStatus.NOT_FOUND) : ResponseEntity.ok(car);
    }

    @GetMapping(value = "/by-year")
    public @ResponseBody ResponseEntity<List<Car>> findByYear(@RequestParam int year) {
        List<Car> list = carsDaoImpl.findByYear(year);
        return list.isEmpty() ? ResponseEntity.ok(new LinkedList<Car>()) : ResponseEntity.ok(list);
    }

    @GetMapping(value = "/by-years-range")
    public ResponseEntity<List<Car>> findByYearsRange(@RequestParam int begin, @RequestParam int end) {
        List<Car> list = carsDaoImpl.findByYearsRange(begin, end);
        return list.isEmpty() ? ResponseEntity.ok(new LinkedList<Car>()) : ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car newCar) {
        boolean added = carsDaoImpl.addCar(newCar.getBrand(), newCar.getModel(), newCar.getColor(), newCar.getProductionYear());
        return added ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
