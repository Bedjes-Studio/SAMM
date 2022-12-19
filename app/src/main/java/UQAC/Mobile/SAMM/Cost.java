package UQAC.Mobile.SAMM;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import UQAC.Mobile.SAMM.APIPojo.CostGetAll;

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

    public Cost(CostGetAll.Response response) {
        this.id = response.id;
        this.value = response.value;
        this.reason = response.reason;
        this.paymentMethod = response.paymentMethod;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            this.date = format.parse(response.date);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }        this.mileage = response.mileage;
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
