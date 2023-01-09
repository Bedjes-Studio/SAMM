package UQAC.Mobile.SAMM.API.APIPojo;

import com.google.gson.annotations.SerializedName;

import UQAC.Mobile.SAMM.Base.Earning;

public class CreateEarning {
    public static class Request {

        @SerializedName("mileage")
        public int mileage;

        @SerializedName("carId")
        public String carId;

        @SerializedName("value")
        public float value;

        @SerializedName("reason")
        public String reason;


        public Request(Earning earning, String carId) {
            this.mileage = earning.getMileage();
            this.carId = carId;
            this.value = earning.getValue();
            this.reason = earning.getReason();
        }
    }
}
