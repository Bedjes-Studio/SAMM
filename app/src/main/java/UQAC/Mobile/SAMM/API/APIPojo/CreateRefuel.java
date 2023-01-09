package UQAC.Mobile.SAMM.API.APIPojo;

import com.google.gson.annotations.SerializedName;

import UQAC.Mobile.SAMM.Base.Refuel;

public class CreateRefuel {
    public static class Request {
        @SerializedName("mileage")
        public int mileage;

        @SerializedName("carId")
        public String carId;

        @SerializedName("fuelType")
        public String fuelType;

        @SerializedName("litterPrice")
        public Float litterPrice;

        @SerializedName("totalCost")
        public Float totalCost;

        @SerializedName("litter")
        public Float litter;

        public Request(Refuel refuel, String carId) {
            this.mileage = refuel.getMileage();
            this.carId = carId;
            this.fuelType = refuel.getFuelType();
            this.litterPrice = refuel.getLitterPrice();
            this.totalCost = refuel.getTotalCost();
            this.litter = refuel.getLitter();
        }
    }
}
