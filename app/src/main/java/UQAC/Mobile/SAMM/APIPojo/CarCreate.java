package UQAC.Mobile.SAMM.APIPojo;

import com.google.gson.annotations.SerializedName;

import UQAC.Mobile.SAMM.Car;

public class CarCreate {

    public static class Request {

        @SerializedName("mileage")
        public int mileage;

        @SerializedName("year")
        public int year;

        @SerializedName("fuelType")
        public String fuelType;

        @SerializedName("fuelCapacity")
        public Float fuelCapacity;

        @SerializedName("type")
        public String type;

        @SerializedName("brand")
        public String brand;

        @SerializedName("model")
        public String model;

        @SerializedName("name")
        public String name;

        @SerializedName("__v")
        public String version;

        public Request(Car car) {
            this.mileage = car.getMileage();
            this.year = car.getYear();
            this.fuelType = car.getFuelType();
            this.fuelCapacity = car.getFuelCapacity();
            this.type = car.getType();
            this.brand = car.getBrand();
            this.model = car.getModel();
            this.name = car.getName();
        }
    }
}


