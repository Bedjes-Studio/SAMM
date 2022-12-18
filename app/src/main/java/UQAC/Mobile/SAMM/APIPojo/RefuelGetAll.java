package UQAC.Mobile.SAMM.APIPojo;

import com.google.gson.annotations.SerializedName;

public class RefuelGetAll {
    public static class Response {

        @SerializedName("_id")
        public String id;

        @SerializedName("date")
        public String date;

        @SerializedName("mileage")
        public int mileage;

        @SerializedName("creatorId")
        public String creatorId;

        @SerializedName("carId")
        public String carId;

        @SerializedName("fuelType")
        public String fuelType;

        @SerializedName("litterPrice")
        public float litterPrice;

        @SerializedName("totalCost")
        public float totalCost;

        @SerializedName("litter")
        public float litter;

        @SerializedName("__v")
        public String version;
    }
}
