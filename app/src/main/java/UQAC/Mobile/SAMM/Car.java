package UQAC.Mobile.SAMM;

import java.util.List;

public class Car {
    private History history;
    private List<User> owners;
    private int mileage;
    private String typeCarbu;
    private double capacityCarbu;
    private String type;
    private String brand;
    private String model;
    private String name;

    public Car(History history, List<User> owners, int mileage, String typeCarbu, double capacityCarbu, String type, String brand, String model, String name) {
        this.history = history;
        this.owners = owners;
        this.mileage = mileage;
        this.typeCarbu = typeCarbu;
        this.capacityCarbu = capacityCarbu;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.name = name;
    }

    //Getters
    public History getHistory() { return history; }
    public List<User> getOwners() { return owners; }
    public int getMileage() { return mileage; }
    public String getType() { return type; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }

    //
}
