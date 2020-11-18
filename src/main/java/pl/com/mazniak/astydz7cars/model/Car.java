package pl.com.mazniak.astydz7cars.model;

import org.springframework.data.annotation.Id;

public class Car {

    @Id
    private long id;
    private String brand;
    private String model;
    private Color color;
    private int productionYear;

    public Car() {}

    public Car(String brand, String model, Color color, int productionYear) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
    }
    public Car(long id, String brand, String model, Color color, int productionYear) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.productionYear = productionYear;
    }

    public long getId() {
        return id;
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color=" + color +
                ", productionYear=" + productionYear +
                '}';
    }

}
