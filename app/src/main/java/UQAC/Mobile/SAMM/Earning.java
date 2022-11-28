package UQAC.Mobile.SAMM;

import java.util.Date;

public class Earning extends Event {
    private float value;
    private String reason;

    public Earning(float value, String reason, Date date, int mileage) {
        this.value = value;
        this.reason = reason;
        this.date = date;
        this.mileage = mileage;
    }
}
