package UQAC.Mobile.SAMM.APIPojo;

import com.google.gson.annotations.SerializedName;

public class CarGetAll {
    public static class Response {

        @SerializedName("_id")
        public String id;

        @SerializedName("mileage")
        public int mileage;

        @SerializedName("year")
        public int year;

        @SerializedName("ownerId")
        public String ownerId;

        @SerializedName("guestsId")
        public String guestsId[];

        @SerializedName("specsId")
        public String specsId;

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
    }
}
