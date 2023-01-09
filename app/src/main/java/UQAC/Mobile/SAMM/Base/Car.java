package UQAC.Mobile.SAMM.Base;

import java.util.List;

import UQAC.Mobile.SAMM.API.APIPojo.GetAllCars;

public class Car {
    private String id;
    private History history;
    private List<User> owners;
    private int mileage;
    private int year;
    private String fuelType;
    private float fuelCapacity;
    private String type;
    private String brand;
    private String model;
    private String name;

    public Car(History history, List<User> owners, int mileage, int year, String fuelType, float fuelCapacity, String type, String brand, String model, String name) {
        this.history = history;
        this.owners = owners;
        this.mileage = mileage;
        this.year = year;
        this.fuelType = fuelType;
        this.fuelCapacity = fuelCapacity;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.name = name;
    }

    public Car(String id) {
        this.id = id;
    }

    public Car(GetAllCars.Response response) {
        this.id = response.id;
        this.mileage = response.mileage;
        this.year = response.year;
        this.fuelType = response.fuelType;
        this.fuelCapacity = response.fuelCapacity;
        this.type = response.type;
        this.brand = response.brand;
        this.model = response.model;
        this.name = response.name;
        // this.owner = get owners from id ?
        // this.guests = get guests from id ?
        // this.specs = not used anymore
    }

    public Car() {
    }

    //Getters
    public String getId() {
        return id;
    }

    public History getHistory() {
        return history;
    }

    public List<User> getOwners() {
        return owners;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public String getFuelType() {
        return fuelType;
    }

    public float getFuelCapacity() {
        return fuelCapacity;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    //
}
