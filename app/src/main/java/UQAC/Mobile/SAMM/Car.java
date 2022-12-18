package UQAC.Mobile.SAMM;

import java.util.List;

import UQAC.Mobile.SAMM.APIPojo.CarGetAll;

public class Car {
    private String id;
    private History history;
    private List<User> owners;
    private int mileage;
    private int year;
    private String typeCarbu;
    private double capacityCarbu;
    private String type;
    private String brand;
    private String model;
    private String name;

    public Car(History history, List<User> owners, int mileage, int year, String typeCarbu, double capacityCarbu, String type, String brand, String model, String name) {
        this.history = history;
        this.owners = owners;
        this.mileage = mileage;
        this.year = year;
        this.typeCarbu = typeCarbu;
        this.capacityCarbu = capacityCarbu;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.name = name;
    }

    public Car(CarGetAll.Response response) {
        this.id = response.id;
        this.mileage = response.mileage;
        this.year = response.year;
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

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    //
}
