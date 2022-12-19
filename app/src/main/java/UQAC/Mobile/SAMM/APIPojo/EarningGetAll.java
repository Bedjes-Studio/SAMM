package UQAC.Mobile.SAMM.APIPojo;

import com.google.gson.annotations.SerializedName;

public class EarningGetAll {
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

        @SerializedName("value")
        public float value;

        @SerializedName("reason")
        public String reason;

        @SerializedName("__v")
        public String version;
    }

    public static class Request {

        @SerializedName("carId")
        public String carId;

        public Request(String id) {
            this.carId = id;
        }
    }
}
