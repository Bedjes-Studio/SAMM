package UQAC.Mobile.SAMM;

import java.util.Date;

public class Cost extends Event {
    private float value;
    private String reason;
    private String paymentMethod;

    public Cost(float value, String reason, String paymentMethod, Date date, int mileage) {
        this.value = value;
        this.reason = reason;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.mileage = mileage;
    }

    public float getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
