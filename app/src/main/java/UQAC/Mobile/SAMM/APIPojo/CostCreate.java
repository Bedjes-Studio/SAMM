package UQAC.Mobile.SAMM.APIPojo;

import com.google.gson.annotations.SerializedName;

import UQAC.Mobile.SAMM.Cost;

public class CostCreate {
    public static class Request {

        @SerializedName("mileage")
        public int mileage;

        @SerializedName("carId")
        public String carId;

        @SerializedName("value")
        public float value;

        @SerializedName("reason")
        public String reason;

        @SerializedName("paymentMethod")
        public String paymentMethod;

        public Request(Cost cost, String carId) {
            this.mileage = cost.getMileage();
            this.carId = carId;
            this.value = cost.getValue();
            this.reason = cost.getReason();
            this.paymentMethod = cost.getPaymentMethod();
        }
    }
}
