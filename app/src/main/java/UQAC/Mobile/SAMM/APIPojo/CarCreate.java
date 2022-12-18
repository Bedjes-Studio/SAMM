package UQAC.Mobile.SAMM.APIPojo;

import com.google.gson.annotations.SerializedName;

public class CarCreate {
    @SerializedName("message")
    public String message;

    public static class Request {
        @SerializedName("mileage")
        public int mileage;

        @SerializedName("year")
        public int year;

        public Request(int mileage, int year) {
            this.mileage = mileage;
            this.year = year;
        }
    }
}


